import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import {UrlService} from '../services/url.service';
import {Workout} from '../myworkout/workout';

@Injectable()
export class WorkoutService {

  constructor(private http: HttpClient,
              private url: UrlService) { }

  getAll(): Observable<String>{
    return this.http
    .get<string>(this.url.workouts);
  }

  getMine(): Observable<Workout[]>{
    return this.http
      .get<Workout[]>(this.url.myworkouts);
  }

  getWorkout(id: string): Observable<Workout>{
    return this.http.get<Workout>(this.url.workouts + '/' + id);
  }

  updateWorkout(realWorkout: Workout): Observable<number>{

    return this.http.put<number>(this.url.workouts +  '/' + realWorkout.workoutID, realWorkout);

  }

}
