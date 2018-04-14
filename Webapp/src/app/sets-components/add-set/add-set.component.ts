import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material';
import {Sets} from '../sets/sets';
import {SetService} from '../../services/set.service';

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
  addToDB = true;

  // distance variables
  KM: Array<number> = new Array();
  M: Array<number> = new Array();

  // time variables
  hours: Array<number> = new Array();
  minutes: Array<number> = new Array();
  seconds: Array<number> = new Array();

  sets: Array<Sets> = new Array();
  callbackCount = 0;

  constructor(@Inject(MAT_DIALOG_DATA) data: any,
              private setService: SetService) {
    this.modalData = data;
    this.exercise = data.exercise;
    this.setsID = data.setsID;
    this.startPos = data.startPos;
    this.parent = data.parent;
    if (data.addToDB !== undefined){
      this.addToDB = data.addToDB;
    }
  }

  ngOnInit() {
    this.addExtra();
  }

  create(): void{

    for (let i = 0; i < this.sets.length; i++){

      // distance tab
      if (this.index === 2){

        // convert hours and mins to seconds
        let sTemp = this.seconds[i];
        let mTemp = this.minutes[i];
        const hTemp = this.hours[i];

        mTemp = mTemp + (hTemp * 60);
        sTemp = sTemp + (mTemp * 60);

        // convert distance to metre
        let metTemp = this.M[i];
        const kmTemp = this.KM[i];

        metTemp = metTemp + (kmTemp * 1000);

        if (metTemp > 0){
          this.sets[i].distance = metTemp;
        }

        if (sTemp > 0){
          this.sets[i].time = sTemp;
        }
      }

      // timed tab
      if (this.index === 1){

        // convert hours and mins to seconds
        let sTemp = this.seconds[i];
        let mTemp = this.minutes[i];
        const hTemp = this.hours[i];

        mTemp = mTemp + (hTemp * 60);
        sTemp = sTemp + (mTemp * 60);

        if (sTemp > 0){
          this.sets[i].time = sTemp;
        }
      }

      const temp = this.sets[i].exercise.exerciseID;
      this.sets[i].exercise = {};
      this.sets[i].exercise.exerciseID = temp;
      if (this.addToDB){
        this.setService.addSet(this.setsID, this.sets[i]).subscribe(
          resp => {
            this.callback(resp);
          }
        );
      }else{
        this.callback(1);
      }
    }
  }

  addExtra(): void{
    this.addSet();
    this.addDistance();
  }

  addSet(): void{
    const temp = new Sets;
    temp.setID = this.setsID;
    temp.exercise = this.exercise;
    temp.position = this.startPos;
    temp.reps = 0;
    temp.weight = 0;
    temp.time = 0;
    temp.speed = 0;
    temp.distance = 0;
    this.startPos++;
    this.sets.push(temp);
  }

  addDistance(): void{
    this.seconds.push(0);
    this.minutes.push(0);
    this.hours.push(0);

    this.KM.push(0);
    this.M.push(0);
  }

  tabChange(event): void{
    this.index = event.index;
  }

  callback(result): void{
    if (result === 1){
      this.callbackCount++;
    }

    if (this.callbackCount === this.sets.length){
      if (this.addToDB) {
        this.parent.getSets();
      }else{
        this.parent.updateSet(this.sets);
      }
      this.parent.dialogRef.close();
    }
  }

  removeSet(index: number): void{
    for (let i = index; i < this.sets.length; i++){
      this.sets[i].position--;
    }
    this.sets.splice(index, 1);
  }

}
