import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ExerciseService} from './exercise.service';
import {Exercise} from './exercise';
import {MatTableDataSource, MatPaginator, MatDialog, MatSort} from '@angular/material';
import {CreateExerciseComponent} from '../create-exercise/create-exercise.component';

@Component({
  selector: 'app-exercise',
  templateUrl: './exercise.component.html',
  styleUrls: ['./exercise.component.css']
})
export class ExerciseComponent implements OnInit, AfterViewInit {

  datasource = new MatTableDataSource();
  columns = ['exerciseName', 'exerciseCategory', 'bodypart', 'description'];
  datalength;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  dialogRef;

  constructor(private exerciseService: ExerciseService, private dialog: MatDialog) { }

  ngOnInit() {
    this.getExercises();
  }

  ngAfterViewInit(): void {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }

  getExercises(): void{
    this.exerciseService.getAll().subscribe(
      resp => {
        this.datasource.data = resp;
        this.datalength = resp.length;
      }
    );
  }

  createExercise(): void{
    this.dialogRef = this.dialog.open(CreateExerciseComponent, {data:{parent:this}});
  }

}
