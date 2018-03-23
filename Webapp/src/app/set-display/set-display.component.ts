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
    if (Number(temp.time) !== 0){
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
      this.display = this.reduceDistance(this.Set.distance);
      if (this.time){
        this.altDisplay = this.reduceHours(this.Set.time);
      }else if (this.speed){
        this.altDisplay = this.Set.speed + ' KM/H';
      }
    }else if (this.time){
      this.display = this.reduceHours(this.Set.time);
    }
  }

  deleteRecord(): void{
    this.update.emit(this.Set);
  }

  reduceDistance(total: number): string{
    let ret = '';
    if (total >= 1000){

      const mRem = total % 1000;
      if (mRem < 100 && mRem > 0){
        ret = Math.floor(total / 1000) + ' KM ' + mRem + ' M';
      }else{
        ret = (total / 1000) + ' KM';
      }

    }else{
      ret = total + ' M';
    }
    return ret;
  }

  reduceHours(total: number): string{
    let ret = '';

    // reduce to hours
    if (total >= 3600){

      const Hours = Math.floor(total / 3600);
      const minsRem = total % 3600;

      ret += Hours + ' Hour';

      if (minsRem > 0){
        const mins = Math.floor(minsRem / 60);
        const secs = minsRem % 60;
        ret += ' ' + mins + ' Min';

        if (secs > 0){
          ret += ' ' + secs + ' Secs';
        }

      }

    // reduce to minutes
    } else if (total > 60){

      const minsRem = total % 3600;
      const mins = Math.floor(minsRem / 60);
      const secs = minsRem % 60;
      ret += ' ' + mins + ' Min';

      if (secs > 0){
        ret += ' ' + secs + ' Secs';
      }

    // reduces to seconds
    }else{
      ret = total + ' Seconds';
    }

    return ret;
  }

}
