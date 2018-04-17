import { Component, OnInit } from '@angular/core';
import {UserService} from '../user-components/user/user.service';
import {Router} from '@angular/router';
import {ConnectionService} from '../services/connection.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  offline = false;

  constructor(public userService: UserService,
              private router: Router,
              private connectionS: ConnectionService) { }
  toggleNavbar;

  ngOnInit() {
    this.connectionS.check().then(() => {
      this.offline = false;
    }).catch(() => {
      this.offline = true;
    });
  }

  navigate(navTo): void{

    this.router.navigate([navTo]);
    this.toggleNavbar = false;

  }

}
