import { Component, OnInit } from '@angular/core';
import {GoalService} from './goal.service';

@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.css']
})
export class GoalsComponent implements OnInit {

  constructor(private goalService: GoalService) { }

  ngOnInit() {
    this.getGoals();
  }

  getGoals(){
    this.goalService.getMine().subscribe(
      resp => console.log(resp)
    );
  }

}
