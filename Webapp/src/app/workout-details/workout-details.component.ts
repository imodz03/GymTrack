import {Component, OnInit} from '@angular/core';

import {ActivatedRoute} from '@angular/router';
import {WorkoutService} from '../workout/workout.service';
import {Workout} from '../myworkout/workout';
import {ExerciselistService} from '../services/exerciselist.service';
import {ExerciseService} from '../exercise/exercise.service';
import {Exercise} from '../exercise/exercise';

import {MatSnackBar} from '@angular/material';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs/Observable';
import {startWith} from 'rxjs/operators/startWith';
import {map} from 'rxjs/operators/map';

@Component({
  selector: 'app-workout-details',
  templateUrl: './workout-details.component.html',
  styleUrls: ['./workout-details.component.css']
})

export class WorkoutDetailsComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private workoutService: WorkoutService,
              private elService: ExerciselistService,
              private exerciseService: ExerciseService,
              private snackBar: MatSnackBar) { }

  workout: Workout;
  canEdit = false;
  addItem = false;
  locked = true;
  exercises: Exercise[];
  exerciseNames = [];
  currentInput = '';
  dateInput: Date;

  myControl: FormControl = new FormControl();
  filteredOptions: Observable<string[]>;

  ngOnInit() {
    this.getWorkout();

    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this.filter(value))
      );
  }

  filter(val: string): string[]{
    return this.exerciseNames.filter(option =>
    option.toLowerCase().indexOf(val.toLowerCase()) === 0);
  }

  getWorkout(): void{

    const id = this.route.snapshot.paramMap.get('id');

    this.workoutService.getWorkout(id).subscribe(
      workout => {
        this.workout = workout;
        this.dateInput = new Date(workout.date);
      }
    );

  }

  edit(): void{
    if (!this.canEdit){
      this.canEdit = true;
    }else{
      // copy the workout object to remove the blanks arrays that cause issues in back end

      const temp = this.cleanClone(this.workout);
      temp.date = this.dateInput.getUTCFullYear() + '-' + (this.dateInput.getUTCMonth() + 1 ) + '-' + this.dateInput.getDate();
      this.workoutService.updateWorkout(temp).subscribe(
        resp => {
          if (resp === 1){
            this.canEdit = false;
            this.workout.date = temp.date;
          }else{
            this.snackBar.open('Something went wrong updating your workout', 'Dismiss', {
              duration: 2000
            });
          }
        }
      );

    }
  }

  delete(i: number): void{
    const exID = this.workout.exerciseList.exercises[i].exerciseID;
    const elID = this.workout.exerciseList.elid;
    if (this.workout.exerciseList.exercises.length > 1){
      this.elService.deleteExercise(elID, exID).subscribe(
        resp => {
          if (resp === 1){
            this.getWorkout();
          }
        }
      );
    }else{
      this.snackBar.open('Workouts must have one exercuse', 'Dismiss', {
        duration: 2000
      });
    }
  }

  cleanClone(workout: Workout): Workout{

    const temp = JSON.parse(JSON.stringify(workout));
    const elid = temp.exerciseList.elid;

    temp.exerciseList = {};
    temp.exerciseList.elid = elid;

    const userID = temp.user.userID;
    temp.user = {};
    temp.user.userID = userID;

    temp.sets = [];

    return temp;

  }

  enableAddItems(){
    this.addItem = true;
    this.exerciseService.getAll().subscribe(
      resp => {
        this.exercises = resp;
        for (let i = 0; i < resp.length; i++){
          this.exerciseNames.push(resp[i].exerciseName);
        }
      }
    );
  }

  addExercise(): void{
    let found = false;
    for (let i = 0; i < this.exercises.length; i++){
      if (this.exercises[i].exerciseName.toLowerCase() === this.currentInput.toLowerCase()){
        found = true;
        const exTemp = {exerciseID: this.exercises[i].exerciseID};
        this.elService.addExercise(this.workout.exerciseList.elid, exTemp).subscribe(
          resp => {
            if (resp === 1){
              this.getWorkout();
              this.addItem = false;
              this.currentInput = '';
            }else{
              this.snackBar.open('Something went wrong adding your exercise', 'Dismiss', {
                duration: 2000
              });
            }
          }
        );
        break;
      }
    }
    if (!found){
      console.log('Exercise not found');
    }
  }


}
