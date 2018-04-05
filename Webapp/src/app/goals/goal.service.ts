import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UrlService} from '../services/url.service';
import {Observable} from 'rxjs/Observable';
import {Goal} from './goal';

@Injectable()
export class GoalService {

  constructor(private http: HttpClient,
              private url: UrlService) { }

  getMine(): Observable<Goal[]>{
    return this.http.get<Goal[]>(this.url.myGoals);
  }

  createGoal(goal: Goal): Observable<number>{
    return this.http.post<number>(this.url.goal, goal);
  }

  completeGoal(id: string, date: any): Observable<number>{
    return this.http.post<number>(this.url.goal + '/complete/' + id, date);
  }

}
