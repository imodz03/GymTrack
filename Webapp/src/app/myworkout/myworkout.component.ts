import { Component, OnInit } from '@angular/core';
import {WorkoutService} from '../services/workout.service';

@Component({
  selector: 'app-myworkout',
  templateUrl: './myworkout.component.html',
  styleUrls: ['./myworkout.component.css']
})
export class MyworkoutComponent implements OnInit {

  myworkouts;

  constructor(private workoutService: WorkoutService) { }

  ngOnInit() {
    this.workoutService.getMine().subscribe(
      list => {
        this.myworkouts = list;
        console.log(this.myworkouts);
      }
    );
  }

}
