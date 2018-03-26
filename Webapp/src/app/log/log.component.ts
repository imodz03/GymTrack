import {Component, Inject, OnInit} from '@angular/core';
import {LogService} from './log.service';
import {SetService} from '../services/set.service';
import {MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'app-log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.css']
})
export class LogComponent implements OnInit {

  constructor(private logService: LogService,
              private setService: SetService,
              @Inject(MAT_DIALOG_DATA)public data: any) { }

  ngOnInit() {
  }

}
