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

@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.css']
})
export class GoalsComponent implements OnInit {

  // viewing goals
  goals: Array<Goal>;
  loaded = false;

  // creating goals
  exercises: Array<Exercise>;
  exerciseNames: Array<string> = new Array<string>();
  allExercises: Array<Exercise>;
  goal: Goal = new Goal();

  myControl: FormControl = new FormControl();
  filteredOptions: Observable<string[]>;

  constructor(private goalService: GoalService,
              private setService: SetService,
              private exerciseService: ExerciseService) { }

  ngOnInit() {
    this.getGoals();
    this.getExercises();
    this.createGoal();

    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this.filter(value))
      );
  }

  filter(val: string): string[]{
    return this.exerciseNames.filter(option =>
      option.toLowerCase().indexOf(val.toLowerCase()) === 0);
  }

  getGoals(): void{
    this.goalService.getMine().subscribe(
      resp => {
        this.goals = resp;
        this.getSets();
      }
    );
  }

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

  addExercise(): void{
    console.log(this.goal);
  }

  createGoal(){
    this.goal = new Goal();
    this.goal.set = new Array<Sets>();
  }

}
