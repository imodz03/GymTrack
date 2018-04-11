import {Component, Input, OnInit} from '@angular/core';
import {Sets} from './sets';
import {Exercise} from '../../exercise-components/exercise/exercise';
import {SetService} from '../../services/set.service';
import {MatDialog} from '@angular/material';
import {AddSetComponent} from '../add-set/add-set.component';
import {ConnectionService} from '../../connection.service';

@Component({
  selector: 'app-sets',
  templateUrl: './sets.component.html',
  styleUrls: ['./sets.component.css']
})
export class SetsComponent implements OnInit {

  @Input()
  exercise: Exercise;

  @Input()
  setsID: string;

  @Input()
  delete: boolean;

  @Input()
  add = true;

  @Input()
  save = false;

  sets = new Array<Sets>();
  dialogRef;

  constructor(private setService: SetService,
              private dialog: MatDialog,
              private connectionS: ConnectionService) { }

  ngOnInit() {
    this.connectionS.check().then(() => {
      this.getSets();
    }).catch(() => {
      this.loadSets();
    });
  }

  loadSets(): void{
    this.sets = JSON.parse(localStorage.getItem('set-' + this.setsID + this.exercise.exerciseID));
  }

  getSets(): void{
    if (this.setsID !== ''){
      this.setService.getSet(this.setsID, this.exercise.exerciseID).subscribe(
        resp => {
          this.sets = resp;
          if (this.save && this.sets.length > 0){
            localStorage.setItem('set-' + this.setsID + this.exercise.exerciseID, JSON.stringify(this.sets));
          }else{
            localStorage.removeItem('set-' + this.setsID + this.exercise.exerciseID);
          }
        }
      );
    }
  }

  deleteSet(event): void{
    this.setService.deleteSet(event.suid).subscribe(
      resp => {
        if (resp === 1){
          this.getSets();
        }
      }
    );
  }

  addSet(): void{
    let startPos = 1;
    if (this.sets.length > 0 ){
      const length = this.sets.length;
      startPos = this.sets[length - 1].position + 1;
    }
    this.dialogRef = this.dialog.open(AddSetComponent,
      {data: {setsID: this.setsID, exercise: this.exercise, startPos: startPos, parent: this }});
  }

}
