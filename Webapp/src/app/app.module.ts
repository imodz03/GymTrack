import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here


import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { LoginComponent } from './login/login.component';
import { WorkoutComponent } from './workout/workout.component';

import { AppRoutingModule } from './/app-routing.module';

import { LoginService } from './services/login.service';
import { WorkoutService } from './services/workout.service';
import { UrlService } from './services/url.service';
import { UserService } from './services/user.service';
import { UserComponent } from './user/user.component';
import { HeaderService } from './services/header.service';
import { MyworkoutComponent } from './myworkout/myworkout.component';
import { RegisterComponent } from './register/register.component';
import {NgbCollapseModule, NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    LoginComponent,
    WorkoutComponent,
    UserComponent,
    MyworkoutComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    NgbCollapseModule
  ],
  providers: [
    LoginService,
    WorkoutService,
    UrlService,
    UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HeaderService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
