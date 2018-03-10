import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { LoginComponent } from './login/login.component';
import { WorkoutComponent } from './workout/workout.component';

import { AppRoutingModule } from './app-routing.module';

import { LoginService } from './login/login.service';
import { WorkoutService } from './workout/workout.service';
import { UrlService } from './services/url.service';
import { UserService } from './user/user.service';
import { UserComponent } from './user/user.component';
import { HeaderService } from './services/header.service';
import {CreateNewDialog, MyworkoutComponent} from './myworkout/myworkout.component';
import { RegisterComponent } from './register/register.component';
import { NgbCollapseModule, NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { WorkoutDetailsComponent } from './workout-details/workout-details.component';
import { ExerciselistService } from './services/exerciselist.service';
import { ExerciseService } from './exercise/exercise.service';
import { ExerciseComponent } from './exercise/exercise.component';
import {
  MatAutocompleteModule, MatCheckboxModule, MatDatepickerModule, MatDialogModule, MatNativeDateModule,
  MatSlideToggleModule, MatSnackBarModule, MatProgressSpinnerModule, MatSelectModule, MatTableModule,
  MatPaginatorModule, MatSortModule, MatTabsModule, MatGridListModule
} from '@angular/material';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import { CreateWorkoutComponent } from './create-workout/create-workout.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateExerciseComponent } from './create-exercise/create-exercise.component';
import { PlanComponent } from './plan/plan.component';
import {PlanService} from './plan/plan.service';

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
    CreateNewDialog,
    DashboardComponent,
    CreateExerciseComponent,
    PlanComponent
  ],
  entryComponents:[
    CreateNewDialog
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
    MatGridListModule
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
    PlanService
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
