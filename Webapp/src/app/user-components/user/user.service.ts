import { Injectable } from '@angular/core';
import {User} from '../login/User';
import {UrlService} from '../../services/url.service';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {Pref} from '../login/Pref';
import {status} from '../register/status';

@Injectable()
export class UserService {
  constructor(private url: UrlService,
              private http: HttpClient) { }

  user: User = {
    username: localStorage.getItem('username'),
    token: localStorage.getItem('token'),
    pass: ''
  };

  prefs: Pref = {
    metric: true
  };

  getUser(): User{
    if (this.user.username === null){
      return null;
    }else{
      return this.user;
    }
  }

  getUserPrefs(): Pref{

    const sPref = localStorage.getItem('prefs');

    if (sPref != null){
      this.prefs = JSON.parse(sPref);
    }

    if (this.prefs.metric === null){
      return null;
    }else{
      return this.prefs;
    }
  }

  updatePrefs(pref: Pref): void{
    localStorage.setItem('prefs', JSON.stringify(pref));
  }

  logout(): void{

    localStorage.removeItem('username');
    localStorage.removeItem('token');
    localStorage.removeItem('prefs');

  }

  getMe(): Observable<string> {
    return this.http.get<string>(this.url.myaccount);
  }

  getPrefs(): Observable<string>{
    return this.http.get<string>(this.url.myaccount + '/pref');
  }

  update(user): Observable<string> {

    return this.http.put<string>(this.url.account + user.userID, user);

  }

  register(user): Observable<status>{
    return this.http.post<status>(this.url.register, user);
  }

  getToken(): string{
    return localStorage.getItem('token');
  }

  deleteMyAccount(): Observable<any>{
    return this.http.delete(this.url.account + 'deleteMe');
  }


}
