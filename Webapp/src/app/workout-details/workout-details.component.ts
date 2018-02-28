import { Component, OnInit } from '@angular/core';

import {ActivatedRoute} from '@angular/router';
import {WorkoutService} from '../services/workout.service';
import {Workout} from '../myworkout/workout';

@Component({
  selector: 'app-workout-details',
  templateUrl: './workout-details.component.html',
  styleUrls: ['./workout-details.component.css']
})
export class WorkoutDetailsComponent implements OnInit {

  constructor(private route: ActivatedRoute, private workoutService: WorkoutService) { }

  workout: Workout;

  ngOnInit() {
    this.getWorkout();
  }

  getWorkout(): void{

    const id = this.route.snapshot.paramMap.get('id');

    this.workoutService.getWorkout(id).subscribe(
      workout => {
        this.workout = workout;
      }
    );

  }

}
