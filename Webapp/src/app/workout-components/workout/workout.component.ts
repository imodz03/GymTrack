import { Component, OnInit } from '@angular/core';

import { WorkoutService } from './workout.service';

@Component({
  selector: 'app-workout',
  templateUrl: './workout.component.html',
  styleUrls: ['./workout.component.css']
})
export class WorkoutComponent implements OnInit {

  workouts = [];
  workout;

  constructor(private workoutService: WorkoutService) { }

  ngOnInit() {

    this.workoutService.getAll().subscribe(resp => {
      for(var i = 0; i < resp.length; i++){
        this.workouts.push(resp[i]);
      }
    });

  }

  selected(workout): void{
    this.workout = workout;
    console.log(workout);
  }



}
