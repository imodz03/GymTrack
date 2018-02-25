import { Injectable } from '@angular/core';
import {User} from '../login/User';
import {UrlService} from './url.service';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class UserService {
  constructor(private url: UrlService,
              private http: HttpClient) { }

  user: User = {
    username: localStorage.getItem('username'),
    token: localStorage.getItem('token'),
    pass: ''
  };

  getUser(): User{
    if (this.user.username === null){
      return null;
    }else{
      return this.user;
    }
  }

  logout(): void{

    localStorage.clear();

  }

  getMe(): Observable<string> {
    return this.http.get<string>(this.url.myaccount);
  }

  update(user): Observable<string> {

    return this.http.put<string>(this.url.account + user.userID, user);

  }



}
