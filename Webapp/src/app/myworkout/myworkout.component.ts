import { Component, OnInit, ViewChild } from '@angular/core';
import {WorkoutService} from '../workout/workout.service';
import {Workout} from './workout';

import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

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

  @ViewChild('details')private content;

  constructor(private workoutService: WorkoutService,
              private modalService: NgbModal) { }

  ngOnInit() {

    this.workoutService.getMine().subscribe(
      list => {
        for (let i = 0; i < list.length; i++){
          this.myworkouts.push({title: list[i].workoutName, date: list[i].date, workout: list[i], parent: this});
        }
        this.loadCalender();
      }
    );
  }

  open(): void {
    this.modalService.open(this.content);
  }

  loadCalender(): void{
    $('#calendar').fullCalendar({
      events: this.myworkouts,
      eventClick: this.clicked
    });
  }

  clicked(event): void{
    event.parent.selectedWorkout = event.workout;
    console.log(event.parent.selectedWorkout.workoutID);
    event.parent.open();
  }

}
