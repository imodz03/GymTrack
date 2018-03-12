import { Component, OnInit } from '@angular/core';
import {PlanService} from './plan.service';
import {Plan} from './plan';
import {Workout} from '../myworkout/workout';
import {MatDialog} from '@angular/material';
import {WorkoutService} from '../workout/workout.service';
import {AddworkoutComponent} from '../addworkout/addworkout.component';

@Component({
  selector: 'app-plan',
  templateUrl: './plan.component.html',
  styleUrls: ['./plan.component.css']
})
export class PlanComponent implements OnInit {

  tileColour = 'lightgray';
  isWeekend = false;
  dialogRef;

  weekdays = [
    {text: 'Monday', workout: null},
    {text: 'Tuesday', workout: null},
    {text: 'Wednesday', workout: null},
    {text: 'Thursday', workout: null},
    {text: 'Friday', workout: null}
  ];
  weekends = [
    {text: 'Monday', workout: null},
    {text: 'Tuesday', workout: null},
    {text: 'Wednesday', workout: null},
    {text: 'Thursday', workout: null},
    {text: 'Friday', workout: null},
    {text: 'Saturday', workout: null},
    {text: 'Sunday', workout: null}
  ];

  workouts = new Array<Workout>();

  constructor(private planService: PlanService,
              private workoutService: WorkoutService,
              private dialog: MatDialog) { }

  ngOnInit() {
    this.getWorkouts();
  }

  getWorkouts(): void{
    this.workoutService.getMine().subscribe(
      resp => {
        this.workouts = resp;
      }
    );
  }

  addWorkout(i): void{
    this.dialogRef = this.dialog.open(AddworkoutComponent,
      {data: {day: this.weekdays[i].text, index: i, parent: this}});
  }

  callback(index, workout): void{
    if (!this.isWeekend){
      this.weekdays[index].workout = workout;
      this.dialogRef.close();
    }
  }

}
