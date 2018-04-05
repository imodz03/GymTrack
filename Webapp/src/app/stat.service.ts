import { Injectable } from '@angular/core';
import {UrlService} from './services/url.service';
import {Observable} from 'rxjs/Observable';
import {Stat} from './dashboard/stat';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class StatService {

  constructor(private url: UrlService,
              private http: HttpClient) { }

  getStats(exerciseID: string): Observable<Stat[]>{
    return this.http.get<Stat[]>(this.url.stats + '/' + exerciseID);
  }

}
