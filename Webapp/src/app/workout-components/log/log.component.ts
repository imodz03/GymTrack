import {Component, Inject, OnInit} from '@angular/core';
import {LogService} from './log.service';
import {SetService} from '../../services/set.service';
import {MAT_DIALOG_DATA} from '@angular/material';
import {Workout} from '../myworkout/workout';
import {Sets} from '../../sets-components/sets/sets';

@Component({
  selector: 'app-log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.css']
})
export class LogComponent implements OnInit {

  workout: Workout;
  updated = false;

  constructor(private logService: LogService,
              private setService: SetService,
              @Inject(MAT_DIALOG_DATA)public data: any) {

    this.workout = data.workout;

  }

  ngOnInit() {
  }

  update(event, i): void{
    this.workout.exerciseList.exercises[i].Set = event;
  }

  createLog(): void{
    const sets = new Array<Sets>();

    for (let i = 0; i < this.workout.exerciseList.exercises.length; i++){
      const tempSet = this.workout.exerciseList.exercises[i].Set;

      for (let x = 0; x < tempSet.length; x++){

        const id = tempSet[x].exercise.exerciseID;
        delete tempSet[x].exercise;
        tempSet[x].exercise = {};
        tempSet[x].exercise.exerciseID = id;

        sets.push(tempSet[x]);
      }

    }

    console.log(sets);

    this.logService.createLog(this.workout.workoutID, sets).subscribe(
      resp => console.log(resp)
    );
  }

}
