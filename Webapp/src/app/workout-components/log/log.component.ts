import {Component, Inject, OnInit} from '@angular/core';
import {LogService} from './log.service';
import {SetService} from '../../services/set.service';
import {MAT_DIALOG_DATA} from '@angular/material';
import {Workout} from '../myworkout/workout';

@Component({
  selector: 'app-log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.css']
})
export class LogComponent implements OnInit {

  workout: Workout;

  constructor(private logService: LogService,
              private setService: SetService,
              @Inject(MAT_DIALOG_DATA)public data: any) {

    this.workout = data.workout;

  }

  ngOnInit() {
  }

}
