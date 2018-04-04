import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {UrlService} from './url.service';
import {Sets} from '../sets-components/sets/sets';

@Injectable()
export class SetService {

  constructor(private http: HttpClient,
              private url: UrlService) { }

  getSet(id, exid): Observable<Sets[]>{
    return this.http.get<Sets[]>(this.url.sets + '/' + id + '/' + exid);
  }

  deleteSet(id): Observable<number>{
    return this.http.delete<number>(this.url.sets + '/' + id);
  }

  addSet(id, set): Observable<number>{
    return this.http.post<number>(this.url.sets + '/' + id, set);
  }

  getSets(id): Observable<Sets[]>{
    return this.http.get<Sets[]>(this.url.sets + '/' + id);
  }

  createList(list: Sets[]): Observable<any>{
    return this.http.post<string>(this.url.sets + '/list', list);
  }

}
