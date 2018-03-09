import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {UrlService} from '../services/url.service';
import {Plan} from './plan';

@Injectable()
export class PlanService {

  constructor(private http: HttpClient,
              private url: UrlService) { }

  getMine(): Observable<Plan[]>{
    return this.http.get<Plan[]>(this.url.myPlan);
  }

}
