import { Component, OnInit } from '@angular/core';
import {PlanService} from './plan.service';
import {Plan} from './plan';

@Component({
  selector: 'app-plan',
  templateUrl: './plan.component.html',
  styleUrls: ['./plan.component.css']
})
export class PlanComponent implements OnInit {

  myPlans: Plan[];

  constructor(private planService: PlanService) { }

  ngOnInit() {
    this.getMine();
  }

  getMine(): void{
    this.planService.getMine().subscribe(
      resp => {
        this.myPlans = resp;
      }
    );
  }

}
