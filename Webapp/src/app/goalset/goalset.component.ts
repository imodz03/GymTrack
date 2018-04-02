import {Component, EventEmitter, Input, Output, OnInit} from '@angular/core';
import {Exercise} from '../exercise-components/exercise/exercise';
import {Sets} from '../sets-components/sets/sets';
import {MatDialog} from '@angular/material';
import {AddSetComponent} from '../sets-components/add-set/add-set.component';

@Component({
  selector: 'app-goalset',
  templateUrl: './goalset.component.html',
  styleUrls: ['./goalset.component.css']
})
export class GoalsetComponent implements OnInit {

  @Input() exercise: Exercise;

  @Output() update: EventEmitter<Sets[]> = new EventEmitter();

  sets: Array<Sets> = new Array();

  dialogRef;

  constructor(private dialog: MatDialog) { }

  ngOnInit() {
  }

  addSet(): void{
    let startPos = 1;
    if (this.sets.length > 0 ){
      const length = this.sets.length;
      startPos = this.sets[length - 1].position + 1;
    }
    this.dialogRef = this.dialog.open(AddSetComponent,
      {data: { exercise: this.exercise, startPos: startPos, parent: this , addToDB: false}});
  }

  updateSet(sets){
    for (let i = 0; i < sets.length; i++){
      this.sets.push(sets[i]);
    }

    this.updateParent();
  }

  delete(set: Sets){
    const index = this.sets.indexOf(set);

    for (let i = index; i < this.sets.length; i++){
      this.sets[i].position--;
    }
    this.sets.splice(index, 1);
    this.updateParent();
  }

  updateParent(): void{
    this.update.emit(this.sets);
  }

}
