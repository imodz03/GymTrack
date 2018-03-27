import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { User } from './User';
import { UrlService } from '../../services/url.service';

@Injectable()
export class LoginService {
  constructor(private http: HttpClient, private urls: UrlService) { }

  login(user: User): Observable<User> {
    return this.http.post<User>(this.urls.login, user);
  }

  verifyToken(user: User): Observable<boolean>{
    return this.http.post<boolean>(this.urls.verify, user);
  }

}
