import {Component, OnInit, ViewChild} from '@angular/core';
import {WorkoutService} from '../workout/workout.service';
import {Workout} from './workout';

import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

import * as $ from 'jquery';
import 'fullcalendar';
import {MatDialog} from '@angular/material';
import {CreateWorkoutComponent} from '../create-workout/create-workout.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-myworkout',
  templateUrl: './myworkout.component.html',
  styleUrls: ['./myworkout.component.css']
})
export class MyworkoutComponent implements OnInit {
  // todo  convert workout popup to mat dialog as component
  myworkouts = [];
  selectedWorkout: Workout;
  selectedDate: string;
  dialogRef;
  newWorkout = new Workout();
  selectedEvent;

  @ViewChild('details')private content;

  constructor(private workoutService: WorkoutService,
              private modalService: NgbModal,
              private dialog: MatDialog,
              private router: Router) { }

  ngOnInit() {
    this.getWorkouts();
  }

  getWorkouts(): void{
    this.myworkouts = new Array<Workout>();
    this.workoutService.getMine().subscribe(
      list => {
        for (let i = 0; i < list.length; i++){
          this.myworkouts.push({title: list[i].workoutName, date: list[i].date, workout: list[i], parent: this});
        }
        if (list.length === 0){
          this.myworkouts.push({parent: this});
        }
        this.loadCalender();
      }
    );
  }

  openQuickView(): void {
    this.dialogRef = this.modalService.open(this.content);
  }

  loadCalender(): void{
    $('#calendar').fullCalendar({
      events: this.myworkouts,
      eventClick: this.clicked,
      dayClick: this.selectDate
    });
  }

  clicked(event): void{
    event.parent.selectedEvent = event;
    event.parent.selectedWorkout = event.workout;
    event.parent.openQuickView();
  }

  selectDate(p1, p2, p3): void{
    const parent = p3.options.events[0].parent;
    parent.selectedDate = this.selectedDate
      = p1._d.getUTCFullYear() + '-' + (p1._d.getMonth() + 1) + '-' + p1._d.getDate();

    parent.dialogRef = parent.dialog.open(CreateWorkoutComponent, {
      data: {date: parent.selectedDate, parent: parent, workout: parent.newWorkout}
    });

    parent.dialogRef.beforeClose().subscribe(val => {
      parent.newWorkout = new Workout();
    });

  }

  delete(): void{
    this.workoutService.deleteWorkout(this.selectedWorkout.workoutID).subscribe(
      resp => {
        if (resp === 1){
          this.dialogRef.close();
          this.getWorkouts();
          $('#calendar').fullCalendar('removeEvents', [this.selectedEvent._id]);
        }
      }
    );
  }

}
