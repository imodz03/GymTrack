import {Component, EventEmitter, OnInit} from '@angular/core';
import {PlanService} from './plan.service';
import {Plan} from './plan';
import {Workout} from '../myworkout/workout';
import {MatDialog, MatSnackBar, MatTabChangeEvent, MatTableDataSource} from '@angular/material';
import {WorkoutService} from '../workout/workout.service';
import {AddworkoutComponent} from '../addworkout/addworkout.component';
import {PlannedWorkout} from './PW';
import {ActivatedRoute, Router} from '@angular/router';
import {SimpleDialogComponent} from '../../simple-dialog/simple-dialog.component';

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
  delDialogRef;
  loading = false; // add loading spinner
  dateInput;
  indexChange = new EventEmitter<MatTabChangeEvent>();
  index = 0;

  columns = ['workoutName', 'date', 'goto'];
  datasource = new Array();

  days = [
    {text: 'Monday', workout: null, val: 'MONDAY'},
    {text: 'Tuesday', workout: null, val: 'TUESDAY'},
    {text: 'Wednesday', workout: null, val: 'WEDNESDAY'},
    {text: 'Thursday', workout: null, val: 'THURSDAY'},
    {text: 'Friday', workout: null, val: 'FRIDAY'}
  ];

  daysn = [
    {text: 'Day 1', workout: {workoutName: 'rest', workoutID: ''}, val: 1}
  ];

  workouts = new Array<Workout>();
  plans = new Array<Plan>();
  plan = new Plan();

  constructor(private planService: PlanService,
              private workoutService: WorkoutService,
              private dialog: MatDialog,
              private snackbar: MatSnackBar,
              private router: Router) { }

  ngOnInit() {
    this.getWorkouts();
    this.getPlans();
    this.plan.planID = '';
    this.plan.repeats = 1;

    this.indexChange.subscribe(
      out => {
        this.index = out.index;
      }
    );

  }

  getPlans(){
    this.planService.getMine().subscribe(
      resp => {
        this.plans = resp;
        this.getPlanWorkouts();
      }
    );
  }

  getPlanWorkouts(): void{
    for (let i = 0; i < this.plans.length; i++){
      this.datasource.push(new MatTableDataSource());
      this.planService.getPlanWorkouts(this.plans[i].planID).subscribe(
        resp => {
          if (resp !== null){
            this.plans[i].workout = resp;
            this.datasource[i].data = resp;
          }
        }
      );
    }
  }

  getWorkouts(): void{
    this.workoutService.getMine().subscribe(
      resp => {
        this.workouts = resp;
      }
    );
  }

  addWorkout(i): void{
    if (this.index === 0){
      this.dialogRef = this.dialog.open(AddworkoutComponent,
        {data: {day: this.days[i].text, index: i, parent: this, selected: this.days[i].workout}});
    }else{
      this.dialogRef = this.dialog.open(AddworkoutComponent,
        {data: {day: this.daysn[i].text, index: i, parent: this, selected: this.daysn[i].workout}});
    }
  }

  callback(index, workout): void{

    if (this.index === 0){
      this.days[index].workout = workout;
    }else{
      this.daysn[index].workout = workout;
    }

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

    if (this.index === 0){
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
    }else if (this.index === 1){
      for (let i = 0; i < this.daysn.length; i++){
        if  (this.daysn[i].workout != null){

          const temp = new PlannedWorkout();
          temp.pwID = '';
          temp.planID = id;
          temp.workoutDay = i + 1;
          temp.workout = {};
          temp.workout.workoutID = this.daysn[i].workout.workoutID;
          temp.workout.workoutName = this.daysn[i].workout.workoutName;
          toPost.push(temp);

        }
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

  addDay(): void{
    this.daysn.push({text: 'Day ' + (this.daysn.length + 1), workout: {workoutName: 'rest', workoutID: ''}, val: (this.daysn.length + 1)});
  }

  details(workoutid): void{
    this.router.navigate(['/workouts/details/' + workoutid]);
  }

  deletePlan(index: number): void{

    this.delDialogRef = this.dialog.open(SimpleDialogComponent,
      { data: {msg: 'Are you sure you want to delete this workout',
                options: ['Confirm', 'Cancel']}});

    this.delDialogRef.afterClosed().subscribe(
      output => {
        if (output === 'Confirm'){
          this.planService.deletePlan(this.plans[index].planID).subscribe(
            resp => {
              if (resp === 1){
                this.getPlans();
              }
            }
          );
        }
      }
    );


  }

}
