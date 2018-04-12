import { Component, OnInit } from '@angular/core';
import {GoalService} from './goal.service';
import {Goal} from './goal';
import {SetService} from '../services/set.service';
import {Sets} from '../sets-components/sets/sets';
import {Exercise} from '../exercise-components/exercise/exercise';
import {ExerciseService} from '../exercise-components/exercise/exercise.service';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs/Observable';
import {startWith} from 'rxjs/operators/startWith';
import {map} from 'rxjs/operators/map';
import {MatSnackBar} from '@angular/material';
import {Router} from '@angular/router';
import {ConnectionService} from '../connection.service';

@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.css']
})
export class GoalsComponent implements OnInit {

  // viewing goals
  goals: Array<Goal>;
  loaded = false;

  // ui variables
  si = 0;

  // creating goals
  exercises: Array<Exercise> = new Array<Exercise>();
  exerciseNames: Array<string> = new Array<string>();
  allExercises: Array<Exercise>;
  goal: Goal = new Goal();
  targetDate: Date;

  myControl: FormControl = new FormControl();
  filteredOptions: Observable<string[]>;
  currentInput;

  online = true;

  constructor(private goalService: GoalService,
              private setService: SetService,
              private exerciseService: ExerciseService,
              private snackbar: MatSnackBar,
              private router: Router,
              private connectionS: ConnectionService) { }


  // check connection on init
  ngOnInit() {

    this.connectionS.check().then(() => {
      this.getGoals();
      this.getExercises();
      this.newGoal();

      // used for the auto complete when selecting exercise
      this.filteredOptions = this.myControl.valueChanges
        .pipe(
          startWith(''),
          map(value => this.filter(value))
        );
    }).catch(() => {
      this.online = false;
    });
  }

  filter(val: string): string[]{
    return this.exerciseNames.filter(option =>
      option.toLowerCase().indexOf(val.toLowerCase()) === 0);
  }

  // retrieves goals from api
  getGoals(): void{
    this.goalService.getMine().subscribe(
      resp => {
        this.goals = resp;
        console.log(resp);
        // filter default 0 date out

        for (let i = 0; i < this.goals.length; i++){
          if (this.goals[i].targetDate === '1970-01-01'){
            this.goals[i].targetDate = '';
          }
          if (this.goals[i].dateAchieved === '1970-01-01'){
            this.goals[i].dateAchieved = '';
          }
        }

        this.getSets();
      }
    );
  }

  // retrieve sets for the goal from API
  getSets(): void{
    for (let i = 0; i < this.goals.length; i++){
      this.setService.getSets(this.goals[i].set[0].setID).subscribe(
        resp => {
          this.goals[i].set = resp;
          this.loaded = true;
        }
      );

    }
  }

  // retrieve exercises from API
  getExercises(): void{
    this.exerciseService.getAll().subscribe(
      resp => {
        this.allExercises = resp;
        for (let i = 0; i < resp.length; i++){
          this.exerciseNames.push(resp[i].exerciseName);
        }
      }
    );
  }

  // add exercise
  addExercise(): void{
    const check = this.checkExercise(this.currentInput);

    if (check !== null){
      this.exercises.push(check);
      this.currentInput = '';
    }

  }

  // checks if the exercise is a valid one
  checkExercise(name: string): Exercise{
    for (let i = 0; i < this.allExercises.length; i++){
      const temp = this.allExercises[i];
      if (temp.exerciseName === name){
        // returns the exercise object if it matches
        return temp;
      }
    }
    // return null if not found
    return null;
  }

  // reset the goal object
  newGoal(){
    this.goal = new Goal();
    this.goal.set = new Array<Sets>();
  }

  update(event, i): void{
    this.exercises[i].Set = event;
  }

  // create the postable sets objects and posts the
  create(): void{
    const sets = new Array<Sets>();

    for (const exercise of this.exercises) {

      if (exercise.Set !== undefined){
        for (let i = 0; i < exercise.Set.length; i++){
          sets.push(exercise.Set[i]);
        }
      }
    }

    if (sets.length > 0 && this.goal.goalName !== '' && this.goal.goalName !== undefined){
      this.setService.createList(sets).subscribe(
        resp => {
          if (resp !== null){
            this.createGoal(resp.setID);
          }else{
            this.snackbar.open('Something went wrong creating your goal', 'Dismiss', {duration: 10000});
          }
        }
      );
    }else{
      this.snackbar.open('You need to enter a name for your goal and at least one exercise', 'Dismiss', {duration: 10000});
    }

  }

  // creates the postable goal object and posts it
  createGoal(setID): void{
    const temp = new Sets();
    temp.setID = setID;
    this.goal.set.push(temp);

    if (this.targetDate !== undefined && this.targetDate !== null){
      const tempDate = this.targetDate.getUTCFullYear()
        + '-' + (this.targetDate.getMonth() + 1) + '-' + this.targetDate.getDate();
      this.goal.targetDate = tempDate;
    }

    // posts goal to API
    this.goalService.createGoal(this.goal).subscribe(
      resp => {
        if (resp === 1){
          this.getGoals();
          this.exercises = new Array<Exercise>();
          this.goal = new Goal();
          this.snackbar.open('Goal Created', 'Dismiss', {duration: 10000});
        } else {
          this.snackbar.open('Something went wrong creating your goal', 'Dismiss', {duration: 10000});
        }
      }
    );

  }

  // marks the goal complete
  complete(goal: Goal): void{
    // add a confirmation?
    const curDate = new Date();
    const date = curDate.getUTCFullYear()
      + '-' + (curDate.getMonth() + 1) + '-' + curDate.getDate();

    // posts date object
    this.goalService.completeGoal(goal.goalID, date).subscribe(
      resp => {
        if (resp === 1){
          this.getGoals();
        }
      }
    );
  }

  // navigate back to myworkouts
  back(): void{
    this.router.navigate(['/myworkouts']);
  }

}
