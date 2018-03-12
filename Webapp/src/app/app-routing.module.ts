import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { WorkoutComponent } from './workout/workout.component';
import {UserComponent} from './user/user.component';
import {MyworkoutComponent} from './myworkout/myworkout.component';
import {RegisterComponent} from './register/register.component';
import {WorkoutDetailsComponent} from './workout-details/workout-details.component';
import {ExerciseComponent} from './exercise/exercise.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {CreateExerciseComponent} from './create-exercise/create-exercise.component';
import {PlanComponent} from './plan/plan.component';
import {AddworkoutComponent} from './addworkout/addworkout.component';

const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'workouts', component: WorkoutComponent },
  { path: 'workouts/details/:id', component: WorkoutDetailsComponent },
  { path: 'account', component: UserComponent },
  { path: 'myworkouts', component: MyworkoutComponent },
  { path: 'exercises', component: ExerciseComponent },
  { path: 'ce', component: CreateExerciseComponent },
  { path: 'plan', component: PlanComponent},
  { path: 'aw', component: AddworkoutComponent}
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
