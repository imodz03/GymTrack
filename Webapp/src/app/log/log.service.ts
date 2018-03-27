import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UrlService} from '../services/url.service';
import {Observable} from 'rxjs/Observable';
import {Log} from './log';

@Injectable()
export class LogService {

  constructor(private http: HttpClient,
              private url: UrlService) { }

  quickLog(setId, workoutID): Observable<number>{
    return this.http.post<number>(this.url.quickLog + '/' + setId, workoutID);
  }

  getLog(workoutID): Observable<Log>{
    return this.http.get<Log>(this.url.log + '/workout/' + workoutID);
  }

}
