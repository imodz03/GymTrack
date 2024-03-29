import {Component, NgModule, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {LoginService} from './user-components/login/login.service';
import {UserService} from './user-components/user/user.service';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {HeaderService} from './services/header.service';
import {ConnectionService} from './services/connection.service';

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
              private location: Location,
              private connectionS: ConnectionService,
              private headerService: HeaderService){

  }


  ngOnInit(): void {

    this.connectionS.check().then(() => {
      this.headerService.executeUpdate();
    });

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
