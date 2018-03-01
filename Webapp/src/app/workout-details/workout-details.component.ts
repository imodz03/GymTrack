import { Component, OnInit } from '@angular/core';

import {ActivatedRoute} from '@angular/router';
import {WorkoutService} from '../services/workout.service';
import {Workout} from '../myworkout/workout';
import {ExerciselistService} from '../services/exerciselist.service';

@Component({
  selector: 'app-workout-details',
  templateUrl: './workout-details.component.html',
  styleUrls: ['./workout-details.component.css']
})
export class WorkoutDetailsComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private workoutService: WorkoutService,
              private elService: ExerciselistService) { }

  workout: Workout;
  ExerciseList;
  canEdit = false;
  titleUpdateFailed = false;
  addItem = false;

  ngOnInit() {
    this.getWorkout();
  }

  getWorkout(): void{

    const id = this.route.snapshot.paramMap.get('id');

    this.workoutService.getWorkout(id).subscribe(
      workout => {
        this.workout = workout;
      }
    );

  }

  edit(): void{
    if (!this.canEdit){
      this.canEdit = true;
    }else{
      // copy the workout object to remove the blanks arrays that cause issues in back end
      const temp = this.cleanClone(this.workout);

      this.workoutService.updateWorkout(temp).subscribe(
        resp => {
          if (resp === 1){
            this.canEdit = false;
          }else{
            this.titleUpdateFailed = true;
          }
        }
      );

    }
  }

  delete(i: number): void{
    const exID = this.workout.exerciseList.exercises[i].exerciseID;
    const elID = this.workout.exerciseList.elid;
    this.elService.deleteExercise(elID, exID).subscribe(
      resp => {
        if (resp === 1){
          this.getWorkout();
        }
      }
    );
  }

  cleanClone(workout: Workout): Workout{

    const temp = JSON.parse(JSON.stringify(workout));
    const elid = temp.exerciseList.elid;

    temp.exerciseList = {};
    temp.exerciseList.elid = elid;

    const userID = temp.user.userID;
    temp.user = {};
    temp.user.userID = userID;

    temp.sets = [];

    return temp;

  }


}
