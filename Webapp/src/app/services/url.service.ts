import { Injectable } from '@angular/core';

@Injectable()
export class UrlService {

  base = 'http://localhost:8087';
  // base = 'https://elliotbrown.me:8080';

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

  // sets
  sets = this.resource + '/set';

  // plan
  plan = this.resource + '/plan';
  plannedWorkouts = this.resource + '/pw';
  myPlan = this.plan + '/mine';
  addWorkouts = this.plannedWorkouts + '/plan';
  getPlanWorkouts = this.plan + '/workouts';

  log = this.resource + '/log';
  quickLog = this.log + '/quick';
  createLog = this.log + '/create';

  goal = this.resource + '/goal';
  myGoals = this.goal + '/mine';

  // stats
  stats = this.api + '/stats';

  constructor() { }

}
