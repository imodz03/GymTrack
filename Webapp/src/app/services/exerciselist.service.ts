import { Injectable } from '@angular/core';
import {UrlService} from './url.service';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class ExerciselistService {

  constructor(private url: UrlService,
              private http: HttpClient) { }


  deleteExercise(id: string, exId: string): Observable<number>{
    return this.http.delete<number>(this.url.el + '/' + id + '/' + exId);
  }

}
