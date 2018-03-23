import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {UrlService} from '../services/url.service';
import {Plan} from './plan';
import {PlannedWorkout} from './PW';
import {Workout} from '../myworkout/workout';

@Injectable()
export class PlanService {

  constructor(private http: HttpClient,
              private url: UrlService) { }

  getMine(): Observable<Plan[]>{
    return this.http.get<Plan[]>(this.url.myPlan);
  }

  create(plan: Plan): Observable<any>{
    return this.http.post<any>(this.url.plan, plan);
  }

  addWorkouts(workouts: PlannedWorkout[]): Observable<any>{
    return this.http.post<any>(this.url.addWorkouts, workouts);
  }

  getPlanWorkouts(planId): Observable<Array<Workout>>{
    return this.http.get<Array<Workout>>(this.url.getPlanWorkouts + '/' + planId);
  }

}
