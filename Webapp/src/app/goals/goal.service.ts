import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UrlService} from '../services/url.service';
import {Observable} from 'rxjs/Observable';
import {Goal} from './goal';

@Injectable()
export class GoalService {

  constructor(private http: HttpClient,
              private url: UrlService) { }

  getMine(): Observable<any>{
    return this.http.get<any>(this.url.myGoals);
  }

}
