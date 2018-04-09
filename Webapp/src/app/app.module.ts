import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { LoginComponent } from './user-components/login/login.component';
import { WorkoutComponent } from './workout-components/workout/workout.component';

import { AppRoutingModule } from './app-routing.module';

import { LoginService } from './user-components/login/login.service';
import { WorkoutService } from './workout-components/workout/workout.service';
import { UrlService } from './services/url.service';
import { UserService } from './user-components/user/user.service';
import { UserComponent } from './user-components/user/user.component';
import { HeaderService } from './services/header.service';
import { MyworkoutComponent} from './workout-components/myworkout/myworkout.component';
import { RegisterComponent } from './user-components/register/register.component';
import { NgbCollapseModule, NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { WorkoutDetailsComponent } from './workout-components/workout-details/workout-details.component';
import { ExerciselistService } from './services/exerciselist.service';
import { ExerciseService } from './exercise-components/exercise/exercise.service';
import { ExerciseComponent } from './exercise-components/exercise/exercise.component';
import {
  MatAutocompleteModule, MatCheckboxModule, MatDatepickerModule, MatDialogModule, MatNativeDateModule,
  MatSlideToggleModule, MatSnackBarModule, MatProgressSpinnerModule, MatSelectModule, MatTableModule,
  MatPaginatorModule, MatSortModule, MatTabsModule, MatGridListModule, MatMenuModule, MatChipsModule, MatExpansionModule
} from '@angular/material';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import { CreateWorkoutComponent } from './workout-components/create-workout/create-workout.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateExerciseComponent } from './exercise-components/create-exercise/create-exercise.component';
import { PlanComponent } from './workout-components/plan/plan.component';
import {PlanService} from './workout-components/plan/plan.service';
import { AddworkoutComponent } from './workout-components/addworkout/addworkout.component';
import { SetService } from './services/set.service';
import { SetsComponent } from './sets-components/sets/sets.component';
import { SetDisplayComponent } from './sets-components/set-display/set-display.component';
import { AddSetComponent } from './sets-components/add-set/add-set.component';
import { LogService } from './workout-components/log/log.service';
import { LogComponent } from './workout-components/log/log.component';
import { SimpleDialogComponent } from './simple-dialog/simple-dialog.component';
import { LogsetComponent } from './workout-components/logset/logset.component';
import { GoalsComponent } from './goals/goals.component';
import { GoalService } from './goals/goal.service';
import { GoalsetComponent } from './goalset/goalset.component';
import { StatService } from './stat.service';
import {AuthGuard} from './auth-guard.service';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    LoginComponent,
    WorkoutComponent,
    UserComponent,
    MyworkoutComponent,
    RegisterComponent,
    WorkoutDetailsComponent,
    ExerciseComponent,
    CreateWorkoutComponent,
    DashboardComponent,
    CreateExerciseComponent,
    PlanComponent,
    AddworkoutComponent,
    SetsComponent,
    SetDisplayComponent,
    AddSetComponent,
    LogComponent,
    SimpleDialogComponent,
    LogsetComponent,
    GoalsComponent,
    GoalsetComponent
  ],
  entryComponents:[
    CreateWorkoutComponent,
    AddSetComponent,
    LogComponent,
    SimpleDialogComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    NgbCollapseModule,
    NgbModule.forRoot(),
    ReactiveFormsModule,
    MatAutocompleteModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSnackBarModule,
    NoopAnimationsModule,
    MatSlideToggleModule,
    MatDialogModule,
    MatCheckboxModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatTabsModule,
    MatGridListModule,
    MatMenuModule,
    MatChipsModule,
    MatExpansionModule
  ],
  providers: [
    LoginService,
    WorkoutService,
    UrlService,
    UserService,
    ExerciselistService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HeaderService,
      multi: true
    },
    ExerciseService,
    PlanService,
    SetService,
    LogService,
    GoalService,
    StatService,
    AuthGuard
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
