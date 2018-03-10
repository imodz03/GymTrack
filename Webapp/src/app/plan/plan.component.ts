import { Component, OnInit } from '@angular/core';
import {PlanService} from './plan.service';
import {Plan} from './plan';
import {Workout} from '../myworkout/workout';

@Component({
  selector: 'app-plan',
  templateUrl: './plan.component.html',
  styleUrls: ['./plan.component.css']
})
export class PlanComponent implements OnInit {

  myPlans: Plan[];
  tileColour = 'lightgray';
  isWeekend = false;

  weekdays = [
    {text: 'Monday', workout: null},
    {text: 'Tuesday', workout: null},
    {text: 'Wednesday', workout: null},
    {text: 'Thursday', workout: null},
    {text: 'Friday', workout: null}
  ];
  weekends = [
    {text: 'Sunday', workout: null},
    {text: 'Monday', workout: null},
    {text: 'Tuesday', workout: null},
    {text: 'Wednesday', workout: null},
    {text: 'Thursday', workout: null},
    {text: 'Friday', workout: null},
    {text: 'Saturday', workout: null}
  ];

  constructor(private planService: PlanService) { }

  ngOnInit() {
    this.getMine();
  }

  getMine(): void{
    this.planService.getMine().subscribe(
      resp => {
        this.myPlans = resp;
        console.log(this.myPlans);
      }
    );
  }

  addWorkout(i): void{
    if (!this.isWeekend){
      this.weekdays[i].workout = {};
      this.weekdays[i].workout.workoutName = 'test';
    }
  }

}
