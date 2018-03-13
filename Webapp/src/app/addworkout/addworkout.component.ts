import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from '@angular/material';
import {WorkoutService} from '../workout/workout.service';
import {Workout} from '../myworkout/workout';
import {CreateWorkoutComponent} from '../create-workout/create-workout.component';

@Component({
  selector: 'app-addworkout',
  templateUrl: './addworkout.component.html',
  styleUrls: ['./addworkout.component.css']
})
export class AddworkoutComponent implements OnInit {

  dialogData;
  dialogRef;
  workouts = [];
  selected = new Workout;
  exercises;

  constructor(@Inject(MAT_DIALOG_DATA)public data: any,
              private workoutService: WorkoutService,
              private dialog: MatDialog) {
    this.dialogData = data;
    if (data.selected != null){
      console.log(data.selected);
      this.selected = data.selected;
    }
  }

  ngOnInit() {
    this.getWorkouts();
  }

  getWorkouts(){
    this.workoutService.getMine().subscribe(
      resp => {
        this.workouts = resp;
      }
    );
  }

  addWorkout(): void{
    this.dialogData.parent.callback(this.dialogData.index, this.selected);
  }

  createWorkout(): void{
    this.dialogRef = this.dialog.open(CreateWorkoutComponent, {data: {workout: this.selected, parent: this}});
  }

  callback(resp, workout): void{
    this.dialogRef.close();
    this.getWorkouts();
  }

  selectionChanged(): void{
    this.exercises = this.selected.exerciseList.exercises;
  }

}
