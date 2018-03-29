import { Component, OnInit } from '@angular/core';
import {GoalService} from './goal.service';
import {Goal} from './goal';
import {SetService} from '../services/set.service';

@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.css']
})
export class GoalsComponent implements OnInit {

  goals: Array<Goal>;
  loaded = false;

  constructor(private goalService: GoalService,
              private setService: SetService) { }

  ngOnInit() {
    this.getGoals();
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

}
