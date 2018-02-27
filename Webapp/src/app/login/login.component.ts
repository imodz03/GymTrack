import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { Location } from '@angular/common';

import { User } from './User';

import { LoginService } from '../services/login.service';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService, private location: Location, private userService: UserService) { }

  loginFailed = false;
  @Output() userUpdated: EventEmitter<User> = new EventEmitter();

  user: User = {
    username: 'Brown27',
    pass: 'PasswordTest',
    token: ''
  };

  ngOnInit() {
  }

  login(user: User): void {
    this.loginService.login(user)
    .subscribe(user => {
      console.log(user);
      if ( user.token === 'invalid') {

        // logic for fail
        console.log('login failed');
        this.loginFailed = true;
        this.user.pass = '';

      } else {

        this.user.pass = '';
        this.user.token = user.token;
        localStorage.setItem('username', this.user.username);
        localStorage.setItem('token', this.user.token);
        this.location.go('/account', '');
        this.location.forward();
      }
    });
  }

}
