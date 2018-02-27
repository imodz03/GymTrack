import { Component, OnInit } from '@angular/core';

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
    password: ''

  };

  confPass: '';

  triedConfPass = false;
  passwordsMatch = true;

  constructor() { }

  ngOnInit() {
  }

  signup(): void{
    console.log(this.registerUser);
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

}
