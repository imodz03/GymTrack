import { Component, OnInit } from '@angular/core';
import {UserService} from '../user-components/user/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(public userService: UserService,
              private router: Router) { }
  toggleNavbar;

  ngOnInit() {
  }

  navigate(navTo): void{

    this.router.navigate([navTo]);
    this.toggleNavbar = false;

  }

}
