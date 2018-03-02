import { Component, OnInit } from '@angular/core';
import {UserService} from '../user/user.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerUser = {
    firstname: '',
    surname: '',
    username: '',
    email: '',
    height: 0,
    weight: 0,
    bodyfatPerc: 0,
    password: '',
    preferences: '{ \"metric\" : true }'

  };

  confPass: '';

  triedConfPass = false;
  passwordsMatch = true;

  usernameTaken = false;
  error = false;
  valid = false;
  enterAll = false;

  constructor(private userService: UserService,
              private location: Location) { }

  ngOnInit() {
  }

  signup(): void{
    if (this.valid === true ){
      this.userService.register(this.registerUser).subscribe(
        resp => {
          if (resp.status === 'NA'){
            this.usernameTaken = true;
          }else if (resp.status === 'error'){
            this.error = true;
          } else {
            localStorage.setItem('username', this.registerUser.username);
            localStorage.setItem('token', resp.status);
            this.goHome();
          }
        }
      );
    }else{
      this.enterAll = true;
    }

  }

  validate(): void{
    if (this.registerUser.password !== this.confPass && this.triedConfPass){

      this.passwordsMatch = false;

    }else{

      this.passwordsMatch = true;

    }
  }

  tried(): void{
    this.triedConfPass = true;
  }

  goHome(): void{
    this.location.back();
    this.location.back();
  }

  statusChecking(): void{
    this.validate();

    if (this.registerUser.firstname !== '' && this.registerUser.username !== '' && this.registerUser.email !== '' &&
      this.confPass === this.registerUser.password ){

      this.valid = true;

    }

  }

}
