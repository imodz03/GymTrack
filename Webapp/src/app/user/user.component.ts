import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/user.service';
import {User} from '../login/User';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private userService: UserService) { }

  user;
  edit = false;

  ngOnInit() {
    this.userService.getMe().subscribe(
      resp => {
        console.log(resp);
        this.user = resp;
      }
    );
  }

  update(): void{
    this.edit = true;
  }

  save(): void{
    console.log(this.user);
    this.userService.update(this.user).subscribe(
      resp => {
        console.log(resp);
      }
    );
  }

}
