import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Sets} from '../sets/sets';

@Component({
  selector: 'app-set-display',
  templateUrl: './set-display.component.html',
  styleUrls: ['./set-display.component.css']
})
export class SetDisplayComponent implements OnInit {

  @Input() Set: Sets;

  @Input() delete: boolean;

  @Output() update: EventEmitter<Sets> = new EventEmitter<Sets>();

  displayAlt = false;

  display = '';
  altDisplay = '';

  reps = false;
  weight = false;
  speed = false;
  distance = false;
  time = false;

  constructor() { }

  ngOnInit() {
    const temp = this.Set;
    if (Number(temp.reps) !== 0){
      this.reps = true;
    }
    if (Number(temp.weight) !== 0.0){
      this.weight = true;
    }
    if (Number(temp.speed) !== 0){
      this.speed = true;
    }
    if (Number(temp.distance) !== 0){
      this.distance = true;
    }
    if (Number(temp.timeTaken) !== 0){
      this.time = true;
    }

    this.setUpView();

  }

  toggleDisplayOn(): void{
    if (this.altDisplay !== ''){
      this.displayAlt = true;
    }
  }

  toggleDisplayOff(): void{
    this.displayAlt = false;
  }

  setUpView(): void{
    if (this.reps){
      this.display = this.Set.reps + ' Reps';
      if (this.weight){
        this.altDisplay = this.Set.weight + 'KG';
      }
    }else if (this.distance){
      this.display = this.Set.distance + ' M';
      if (this.time){
        this.altDisplay = this.Set.timeTaken + ' Minutes';
      }else if (this.speed){
        this.altDisplay = this.Set.speed + ' KM/H';
      }
    }else if (this.time){
      this.display = this.Set.timeTaken + ' Minutes';
    }
  }

  deleteRecord(): void{
    this.update.emit(this.Set);
  }

}
