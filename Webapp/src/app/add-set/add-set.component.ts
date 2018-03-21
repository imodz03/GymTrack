import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material';
import {Sets} from '../sets/sets';
import {SetService} from '../services/set.service';

@Component({
  selector: 'app-add-set',
  templateUrl: './add-set.component.html',
  styleUrls: ['./add-set.component.css']
})
export class AddSetComponent implements OnInit {

  modalData: any;
  exercise;
  setsID;
  index = 0;
  startPos = 0;
  parent;

  sets: Array<Sets> = new Array();

  constructor(@Inject(MAT_DIALOG_DATA) data: any,
              private setService: SetService) {
    this.modalData = data;
    this.exercise = data.exercise;
    this.setsID = data.setsID;
    this.startPos = data.startPos;
    this.parent = data.parent;
  }

  ngOnInit() {
    this.addSet();
  }

  create(): void{
    console.log(this.sets);
    let respVal = 0;
    for (let i = 0; i < this.sets.length; i++){
      const temp = this.sets[i].exercise.exerciseID;
      this.sets[i].exercise = {};
      this.sets[i].exercise.exerciseID = temp;
      this.setService.addSet(this.setsID, this.sets[i]).subscribe(
        resp => {
          respVal = resp;
        }
      );
    }
    console.log(respVal);
  }

  addSet(): void{
    const temp = new Sets;
    temp.setID = this.setsID;
    temp.exercise = this.exercise;
    temp.position = this.startPos;
    this.startPos++;
    this.sets.push(temp);
  }

  tabChange(event): void{
    this.index = event.index;
    console.log(this.index);
  }

}
