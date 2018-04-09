import {Component, OnInit, EventEmitter, Output, NgZone} from '@angular/core';

import { User } from './User';

import { LoginService } from './login.service';
import {UserService} from '../user/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService,
              private userService: UserService,
              private router: Router,
              private zone: NgZone) { }

  loginFailed = false;
  @Output() userUpdated: EventEmitter<User> = new EventEmitter();

  user: User = {
    username: '',
    pass: '',
    token: ''
  };

  ngOnInit() {
  }

  login(tryuser: User): void {
    this.loginService.login(tryuser)
    .subscribe(user => {
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
        this.router.navigate(['/']); // todo fix bug where username is not updated without page refresh on login
      }
    });
  }

}
