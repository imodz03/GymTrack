import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SetService} from '../../services/set.service';
import {MatDialog} from '@angular/material';
import {AddSetComponent} from '../../sets-components/add-set/add-set.component';
import {Sets} from '../../sets-components/sets/sets';

@Component({
  selector: 'app-logset',
  templateUrl: './logset.component.html',
  styleUrls: ['./logset.component.css']
})
export class LogsetComponent implements OnInit {

  @Input()
  setsID;

  @Input()
  exercise;

  @Output() update: EventEmitter<Sets[]> = new EventEmitter<Sets[]>();

  sets: Sets[];
  dialogRef;

  constructor(private setService: SetService,
              private dialog: MatDialog) { }

  ngOnInit() {
    this.getSets();
  }

  getSets(): void{
    if (this.setsID !== ''){
      this.setService.getSet(this.setsID, this.exercise.exerciseID).subscribe(
        resp => {
          this.sets = resp;
          this.update.emit(this.sets);
        }
      );
    }
  }

  addSet(): void{
    let startPos = 1;
    if (this.sets.length > 0 ){
      const length = this.sets.length;
      startPos = this.sets[length - 1].position + 1;
    }
    this.dialogRef = this.dialog.open(AddSetComponent,
      {data: {setsID: this.setsID, exercise: this.exercise, startPos: startPos, parent: this , addToDB: false}});
  }

  delete(set): void{

    const pos = this.sets.indexOf(set);

    for (let i = pos; i < this.sets.length; i++){
      this.sets[i].position--;
    }

    this.sets.splice(pos, 1);

    this.update.emit(this.sets);
  }

  updateSet(sets){
    for (let i = 0; i < sets.length; i++){
      this.sets.push(sets[i]);
    }
  }


}
