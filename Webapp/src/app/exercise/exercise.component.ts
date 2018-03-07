import { Component, OnInit } from '@angular/core';
import {ExerciseService} from './exercise.service';
import {Exercise} from './exercise';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-exercise',
  templateUrl: './exercise.component.html',
  styleUrls: ['./exercise.component.css']
})
export class ExerciseComponent implements OnInit {

  exercises: Exercise[];
  datasource = new MatTableDataSource();
  columns = ['exerciseName'];

  constructor(private exerciseService: ExerciseService) { }

  ngOnInit() {
    this.exerciseService.getAll().subscribe(
      resp => {
        this.exercises = resp;
      }
    );
  }

}
