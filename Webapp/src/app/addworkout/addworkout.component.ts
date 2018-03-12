import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material';
import {WorkoutService} from '../workout/workout.service';
import {Workout} from '../myworkout/workout';

@Component({
  selector: 'app-addworkout',
  templateUrl: './addworkout.component.html',
  styleUrls: ['./addworkout.component.css']
})
export class AddworkoutComponent implements OnInit {

  dialogData;
  workouts = [];
  selected: Workout;

  constructor(@Inject(MAT_DIALOG_DATA)public data: any,
              private workoutService: WorkoutService) {
    this.dialogData = data;
  }

  ngOnInit() {
    this.workoutService.getMine().subscribe(
      resp => {
        this.workouts = resp;
      }
    );
  }

  addWorkout(): void{
    this.dialogData.parent.callback(this.dialogData.index, this.selected);
  }

}
