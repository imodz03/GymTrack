import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatSnackBar} from '@angular/material';
import {Router} from '@angular/router';
import {WorkoutService} from '../workout/workout.service';
import {ExerciseService} from '../exercise/exercise.service';
import {Observable} from 'rxjs/Observable';
import {FormControl} from '@angular/forms';
import {Exercise} from '../exercise/exercise';
import {Workout} from '../myworkout/workout';
import {startWith} from 'rxjs/operators/startWith';
import {map} from 'rxjs/operators/map';
import {CreateExerciseComponent} from '../create-exercise/create-exercise.component';

@Component({
  selector: 'app-create-workout',
  templateUrl: './create-workout.component.html',
  styleUrls: ['./create-workout.component.css']
})
export class CreateWorkoutComponent implements OnInit {

  workout: Workout;
  exercises: Exercise[];
  myExercises = new Array<Exercise>();
  exerciseNames = [];
  myControl: FormControl = new FormControl();
  filteredOptions: Observable<string[]>;
  currentInput: string;
  dialogRef;
  initExercise;

  @ViewChild('MatInput') text;

  constructor(@Inject(MAT_DIALOG_DATA)public data: any,
              private exerciseService: ExerciseService,
              private workoutService: WorkoutService,
              private router: Router,
              private snackbar: MatSnackBar,
              private modal: MatDialog){
    this.workout = data.workout;
    this.workout.public = false;
  }

  ngOnInit(){
    this.getExercises();
    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this.filter(value))
      );
    this.workout.exerciseList = {};
    this.workout.exerciseList.exercises = [];
  }

  getExercises(): void{
    this.exerciseService.getAll().subscribe(
      resp => {
        this.exercises = resp;
        for (let i = 0; i < resp.length; i++){
          this.exerciseNames.push(resp[i].exerciseName);

        }
      }
    );
  }

  create(workout: Workout): void{

    if (workout.workoutName !== ''){

      workout.date = this.data.date;
      workout.workoutID = '';

      for (let i = 0; i < workout.exerciseList.exercises.length; i++){
        const id = workout.exerciseList.exercises[i].exerciseID;
        workout.exerciseList.exercises[i] = {};
        workout.exerciseList.exercises[i].exerciseID = id;
      }

      this.workoutService.createWorkout(workout).subscribe(resp => {
        if (resp.resp === 0){
          this.snackbar.open('Something went wrong creating your workout', 'Dismiss', {duration: 10000});
        }else{
          this.data.parent.callback(resp, workout);
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

  createExercise(): void{
    this.dialogRef = this.modal.open(CreateExerciseComponent, {data: {parent: this}});
  }

  addEx(): void{
    const input = this.getExercise(this.currentInput);
    if (input != null && this.initExercise == null){

      this.initExercise = input.exerciseName;
      this.workout.exerciseList.exercises.push(input);
      this.currentInput = '';

    }else if (input != null && this.initExercise != null){

      for ( let i = 0; i < this.workout.exerciseList.exercises.length; i++){
        if  (input === this.workout.exerciseList.exercises[i]){
          this.snackbar.open('Exercise already in list', 'Dismiss', {duration: 1000});
        }else{
          this.workout.exerciseList.exercises.push(input);
          this.myExercises.push(input);
          this.currentInput = '';
          break;
        }
      }

    }else if (input == null){

      this.snackbar.open('Exercise doesn\'t exist', 'Dissmiss', {duration: 10000});

    }
  }


}
