import {Component, Input, OnInit} from '@angular/core';
import {Sets} from './sets';
import {Exercise} from '../exercise/exercise';
import {SetService} from '../services/set.service';

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

  display = true;

  sets = new Array<Sets>();

  constructor(private setService: SetService) { }

  ngOnInit() {
    this.getSets();
  }

  getSets(): void{
    this.setService.getSet(this.setsID, this.exercise.exerciseID).subscribe(
      resp => {
        for (let i = 0; i < resp.length; i++){
          this.sets.push(resp[i]);
        }
      }
    );
  }

}
