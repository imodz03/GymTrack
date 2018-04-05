import { Component, OnInit } from '@angular/core';
import {Router, NavigationExtras} from '@angular/router';
import {ExerciseService} from '../exercise-components/exercise/exercise.service';
import {StatService} from '../stat.service';
import {Exercise} from '../exercise-components/exercise/exercise';
import {Stat} from './stat';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  exercises = new Array<Exercise>();
  stats = new Array<Stat>();

  constructor(private router: Router,
              private exerciseService: ExerciseService,
              private statService: StatService) { }

  ngOnInit() {
    this.getExercises();
  }

  getExercises(): void{
    this.exerciseService.getAll().subscribe(
      resp => {
        this.exercises = resp;
      }
    );
  }

  getStat(Exid): void{
    this.statService.getStats(Exid).subscribe(
      resp => {
        this.stats = resp;
        console.log(this.stats);
      }
    );
  }

  optionSelected(event): void{
    const select = this.exercises[event.value];

    this.getStat(select.exerciseID);
  }

}
