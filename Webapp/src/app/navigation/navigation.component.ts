import {Component, DoCheck, OnInit} from '@angular/core';
import {UserService} from '../user-components/user/user.service';
import {Router} from '@angular/router';
import {ConnectionService} from '../services/connection.service';
import {User} from '../user-components/login/User';

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
  user: User;

  ngOnInit() {
    this.connectionS.check().then(() => {
      this.offline = false;
    }).catch(() => {
      this.offline = true;
    });
    this.update();
  }

  navigate(navTo): void{

    this.router.navigate([navTo]);
    this.toggleNavbar = false;

  }

  update(): void{
    this.user = this.userService.getUser();
  }

}
