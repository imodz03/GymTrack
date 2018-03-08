import { Injectable } from '@angular/core';

@Injectable()
export class UrlService {

  base = 'http://172.17.42.46:8080';

  // auth
  auth = this.base + '/auth';
  login = this.auth + '/login';
  verify = this.auth + '/verify';

  // API
  api = this.base + '/API';
  resource = this.api + '/resource';
  workouts = this.resource + '/workout';
  myworkouts = this.workouts + '/mine';

  account = this.resource + '/user/';
  myaccount = this.resource + '/user/mine';
  register = this.account + 'register';

  // exercise list
  el = this.resource + '/el';

  // exercises
  exercise = this.resource + '/exercise';

  constructor() { }

}
