import { Injectable } from '@angular/core';
import {UrlService} from './url.service';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {Exercise} from '../exercise-components/exercise/exercise';

@Injectable()
export class ExerciselistService {

  constructor(private url: UrlService,
              private http: HttpClient) { }


  deleteExercise(id: string, exId: string): Observable<number>{
    return this.http.delete<number>(this.url.el + '/' + id + '/' + exId);
  }

  addExercise(id: string, exId: any): Observable<number>{
    return this.http.post<number>(this.url.el + '/' + id + '/add', exId);
  }

}
