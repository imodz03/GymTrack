import { Component, OnInit } from '@angular/core';
import {UserService} from '../user/user.service';
import {Router} from '@angular/router';


import {MatSpinner} from '@angular/material';

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
  loading = false;

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit() {
  }

  signup(): void{
    this.loading = true;
    if (this.valid === true ){
      this.userService.register(this.registerUser).subscribe(
        resp => {
          if (resp.status === 'NA'){
            this.usernameTaken = true;
            this.loading = false;
          }else if (resp.status === 'error'){
            this.error = true;
            this.loading = false;
          } else {
            localStorage.setItem('username', this.registerUser.username);
            localStorage.setItem('token', resp.status);
            setTimeout(this.gotTo, 2000, this.router);
          }
        }
      );
    }else{
      this.enterAll = true;
      this.loading = false;
    }

  }

  gotTo(router: Router){
    this.loading = false;
    router.navigate(['']);
  }

  validate(): void{
    if (this.registerUser.password !== this.confPass && this.triedConfPass){

      this.passwordsMatch = false;

    }else{

      this.passwordsMatch = true;

    }
  }

  tried(): void {
    this.triedConfPass = true;
  }

  statusChecking(): void{
    this.validate();

    if (this.registerUser.firstname !== '' && this.registerUser.username !== '' && this.registerUser.email !== '' &&
      this.confPass === this.registerUser.password ){

      this.valid = true;

    }

  }

}
