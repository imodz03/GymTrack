import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './user-components/login/login.component';
import { WorkoutComponent } from './workout-components/workout/workout.component';
import {UserComponent} from './user-components/user/user.component';
import {MyworkoutComponent} from './workout-components/myworkout/myworkout.component';
import {RegisterComponent} from './user-components/register/register.component';
import {WorkoutDetailsComponent} from './workout-components/workout-details/workout-details.component';
import {ExerciseComponent} from './exercise-components/exercise/exercise.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {CreateExerciseComponent} from './exercise-components/create-exercise/create-exercise.component';
import {PlanComponent} from './workout-components/plan/plan.component';
import {AddworkoutComponent} from './workout-components/addworkout/addworkout.component';
import {GoalsComponent} from './goals/goals.component';
import {AuthGuard} from './auth-guard.service';
import {ChatbotComponent} from './chatbot/chatbot.component';

const routes: Routes = [
  {
    path: '',
    canActivate: [AuthGuard],
    children: [
      { path: '', component: DashboardComponent },
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'workouts', component: WorkoutComponent },
      { path: 'workouts/details/:id', component: WorkoutDetailsComponent },
      { path: 'account', component: UserComponent },
      { path: 'myworkouts', component: MyworkoutComponent },
      { path: 'exercises', component: ExerciseComponent },
      { path: 'plan', component: PlanComponent},
      { path: 'goal', component: GoalsComponent},
      { path: 'cb', component: ChatbotComponent }
    ]
  }
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
