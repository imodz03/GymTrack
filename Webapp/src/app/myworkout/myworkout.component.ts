import { Component, OnInit } from '@angular/core';
import {WorkoutService} from '../services/workout.service';
import {Workout} from './workout';

import * as $ from 'jquery';

import 'fullcalendar';

@Component({
  selector: 'app-myworkout',
  templateUrl: './myworkout.component.html',
  styleUrls: ['./myworkout.component.css']
})
export class MyworkoutComponent implements OnInit {

  myworkouts = [];

  selectedWorkout: Workout;

  constructor(private workoutService: WorkoutService) { }

  ngOnInit() {

    this.workoutService.getMine().subscribe(
      list => {
        for (let i = 0; i < list.length; i++){
          this.myworkouts.push({title: list[i].workoutName, date: list[i].date, workout: list[i]});
        }
        this.loadCalender();
      }
    );
  }

  loadCalender(): void{
    $('#calendar').fullCalendar({
      events: this.myworkouts,
      eventClick: this.clicked
    });
  }

  clicked(event): void{
    this.selectedWorkout = event.workout;
    console.log(this.selectedWorkout);
  }

}
