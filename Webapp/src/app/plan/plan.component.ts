import { Component, OnInit } from '@angular/core';
import {PlanService} from './plan.service';
import {Plan} from './plan';
import {Workout} from '../myworkout/workout';
import {MatDialog, MatSnackBar} from '@angular/material';
import {WorkoutService} from '../workout/workout.service';
import {AddworkoutComponent} from '../addworkout/addworkout.component';
import {PlannedWorkout} from './PW';
import {Router} from '@angular/router';

@Component({
  selector: 'app-plan',
  templateUrl: './plan.component.html',
  styleUrls: ['./plan.component.css']
})
export class PlanComponent implements OnInit {

  tileColour = 'lightgray';
  isWeekend = false;
  workoutAdded = false;
  dialogRef;
  loading = false;
  dateInput;

  days = [
    {text: 'Monday', workout: null, val: 'MONDAY'},
    {text: 'Tuesday', workout: null, val: 'TUESDAY'},
    {text: 'Wednesday', workout: null, val: 'WEDNESDAY'},
    {text: 'Thursday', workout: null, val: 'THURSDAY'},
    {text: 'Friday', workout: null, val: 'FRIDAY'}
  ];

  workouts = new Array<Workout>();
  plan = new Plan();

  constructor(private planService: PlanService,
              private workoutService: WorkoutService,
              private dialog: MatDialog,
              private snackbar: MatSnackBar,
              private router: Router) { }

  ngOnInit() {
    this.getWorkouts();
    this.plan.planID = '';
    this.plan.repeats = 1;
  }

  getWorkouts(): void{
    this.workoutService.getMine().subscribe(
      resp => {
        this.workouts = resp;
      }
    );
  }

  addWorkout(i): void{
    this.dialogRef = this.dialog.open(AddworkoutComponent,
      {data: {day: this.days[i].text, index: i, parent: this, selected: this.days[i].workout}});
  }

  callback(index, workout): void{
    this.days[index].workout = workout;
    this.dialogRef.close();
    this.workoutAdded = true;
  }

  toggleWeekend(): void{
    this.isWeekend = !this.isWeekend;
    if  (this.isWeekend){
      this.days.push({text: 'Saturday', workout: null, val: 'SATURDAY'});
      this.days.push({text: 'Sunday', workout: null, val: 'SUNDAY'});
    }else{
      this.days.splice(5);
    }
  }

  createPlan(): void{
    if (this.workoutAdded && this.plan.planName !== '' && this.dateInput !== undefined && this.plan.repeats !== null){
      this.plan.startDate = this.dateInput.getUTCFullYear() + '-' + (this.dateInput.getMonth() + 1) + '-' + this.dateInput.getDate();
      this.planService.create(this.plan).subscribe(
        resp => {
          if (resp !== 0){
            this.postExercises(resp.id);
          }else{

          }
        }
      );
    }else{
      this.snackbar.open('Plans need a name, start date and workouts', 'Dismiss', {duration: 10000});
    }
  }

  postExercises(id: string): void{
    const toPost = new Array<PlannedWorkout>();

    for (let i = 0; i < this.days.length; i++){
      if  (this.days[i].workout != null){

        const temp = new PlannedWorkout();
        temp.pwID = '';
        temp.planID = id;
        temp.dayOfWeek = this.days[i].val;
        temp.workout = {};
        temp.workout.workoutID = this.days[i].workout.workoutID;
        toPost.push(temp);

      }
    }
    this.planService.addWorkouts(toPost).subscribe(
      resp => {
        if (resp === 1){
          this.router.navigate(['/myworkouts']);
        }else{
          this.snackbar.open('Something went wrong creating your plan', 'Dismiss', {duration: 10000});
        }
      }
    );

  }

}
