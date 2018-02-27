import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { WorkoutComponent } from './workout/workout.component';
import {UserComponent} from './user/user.component';
import {MyworkoutComponent} from './myworkout/myworkout.component';
import {RegisterComponent} from './register/register.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'workouts', component: WorkoutComponent },
  { path: 'account', component: UserComponent },
  { path: 'myworkouts', component: MyworkoutComponent }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
