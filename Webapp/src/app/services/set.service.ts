import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {UrlService} from './url.service';
import {Sets} from '../sets/sets';

@Injectable()
export class SetService {

  constructor(private http: HttpClient,
              private url: UrlService) { }

  getSet(id, exid): Observable<Sets[]>{
    return this.http.get<Sets[]>(this.url.sets + '/' + id + '/' + exid);
  }

}
