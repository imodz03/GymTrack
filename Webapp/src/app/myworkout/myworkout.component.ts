import {Component, OnInit, ViewChild, Inject, EventEmitter} from '@angular/core';
import {Location} from '@angular/common';
import {WorkoutService} from '../workout/workout.service';
import {Workout} from './workout';

import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {MAT_DIALOG_DATA, MatCheckboxChange, MatDialogRef, MatSlideToggleChange, MatSnackBar} from '@angular/material';

import * as $ from 'jquery';
import 'fullcalendar';
import {MatDialog} from '@angular/material';
import {Exercise} from '../exercise/exercise';
import {ExerciseService} from '../exercise/exercise.service';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs/Observable';
import {startWith} from 'rxjs/operators/startWith';
import {map} from 'rxjs/operators/map';
import {Router} from '@angular/router';

@Component({
  selector: 'app-myworkout',
  templateUrl: './myworkout.component.html',
  styleUrls: ['./myworkout.component.css']
})
export class MyworkoutComponent implements OnInit {

  myworkouts = [];
  selectedWorkout: Workout;
  selectedDate: string;
  dialogRef;
  newWorkout = new Workout();

  @ViewChild('details')private content;

  constructor(private workoutService: WorkoutService,
              private modalService: NgbModal,
              private dialog: MatDialog) { }

  ngOnInit() {
    this.workoutService.getMine().subscribe(
      list => {
        for (let i = 0; i < list.length; i++){
          this.myworkouts.push({title: list[i].workoutName, date: list[i].date, workout: list[i], parent: this});
        }
        if (list.length === 0){
          this.myworkouts.push({parent: this});
        }
        this.loadCalender();
      }
    );
  }

  openQuickView(): void {
    this.modalService.open(this.content);
  }

  loadCalender(): void{
    $('#calendar').fullCalendar({
      events: this.myworkouts,
      eventClick: this.clicked,
      dayClick: this.selectDate
    });
  }

  clicked(event): void{
    event.parent.selectedWorkout = event.workout;
    event.parent.openQuickView();
  }

  selectDate(p1, p2, p3): void{
    const parent = p3.options.events[0].parent;
    parent.selectedDate = this.selectedDate
      = p1._d.getUTCFullYear() + '-' + (p1._d.getMonth() + 1) + '-' + p1._d.getDate();

    parent.dialogRef = parent.dialog.open(CreateNewDialog, {
      data: {date: parent.selectedDate, parent: parent, workout: parent.newWorkout}
    });

    parent.dialogRef.beforeClose().subscribe(val => {
      parent.newWorkout = new Workout();
    });

  }

}

// class for dialog popup
@Component({
  selector: 'app-create-dialog',
  templateUrl: '../create-workout/create-new-workout-dialog.html'
})
export class CreateNewDialog implements OnInit{
  workout: Workout;
  exercises: Exercise[];
  exerciseNames = [];
  myControl: FormControl = new FormControl();
  filteredOptions: Observable<string[]>;
  currentInput = '';
  typedIn = false;

  constructor(@Inject(MAT_DIALOG_DATA)public data: any,
              private exerciseService: ExerciseService,
              private workoutService: WorkoutService,
              private router: Router,
              private snackbar: MatSnackBar){
    this.workout = data.workout;
    this.workout.public = false;
  }

  ngOnInit(){
    this.exerciseService.getAll().subscribe(
      resp => {
        this.exercises = resp;
        for (let i = 0; i < resp.length; i++){
          this.exerciseNames.push(resp[i].exerciseName);
        }
      }
    );
    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this.filter(value))
      );
  }

  create(workout: Workout): void{
    const ex = this.getExercise(this.currentInput);

    if (ex !== null && workout.workoutName !== ''){
      workout.exerciseList = {};
      workout.exerciseList.exercises = [];
      workout.exerciseList.exercises[0] = {};
      workout.exerciseList.exercises[0].exerciseName = ex.exerciseName;
      workout.exerciseList.exercises[0].exerciseID = ex.exerciseID;
      workout.date = this.data.date;
      workout.workoutID = '';
      this.workoutService.createWorkout(workout).subscribe(resp => {
        if (resp.resp === 0){
          this.snackbar.open('Something went wrong creating your workout', 'Dismiss', {duration: 10000});
        }else{
          this.router.navigate(['/workouts/details/' + resp.resp]);
          this.data.parent.dialogRef.close();
        }
      });
    }else{
      this.snackbar.open('Workouts require a name and one exercise', 'Dismiss', {duration: 10000});
    }

  }

  getExercise(name: string): Exercise{
    for (let i = 0; i < this.exercises.length; i++){
      const temp = this.exercises[i];
      if (temp.exerciseName.toLowerCase() === name.toLowerCase()){
        return this.exercises[i];
      }
    }
    return null;
  }



  filter(val: string): string[]{
    return this.exerciseNames.filter(option =>
      option.toLowerCase().indexOf(val.toLowerCase()) === 0);
  }

}
