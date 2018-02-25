import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import {LoginService} from './services/login.service';
import {UserService} from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(private loginService: LoginService,
              private userService: UserService,
              private location: Location ){

  }


  ngOnInit(): void {

    this.loginService.verifyToken(this.userService.getUser()).subscribe(
      resp => {
        if (resp === true){
          // do nothing token is valid
        }else{
          // log user out because token is invalid
          this.userService.logout();
          this.location.go('/');
        }
      }
    );

  }
}
