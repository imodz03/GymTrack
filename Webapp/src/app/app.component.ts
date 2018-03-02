import {Component, NgModule, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {LoginService} from './login/login.service';
import {UserService} from './user/user.service';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  imports: [NgbModule.forRoot()]
})

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

    if (this.userService.getUser() != null){
      this.userService.getPrefs().subscribe(
        prefs => {
          localStorage.setItem('prefs', JSON.stringify(prefs));
        }
      );
    }

  }
}
