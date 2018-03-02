import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {UrlService} from '../services/url.service';
import {Exercise} from './exercise';

@Injectable()
export class ExerciseService {

  constructor(private http: HttpClient,
              private url: UrlService) { }

  getAll(): Observable<Exercise[]>{
    return this.http.get<Exercise[]>(this.url.exercise);
  }

}
