import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private userService: UserService) { }

  user;
  edit = false;
  editPrefs = false;

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
    const heightM = (this.user.height / 100);

    this.user.bmi = Math.round((this.user.weight / heightM) / heightM);
    this.userService.update(this.user).subscribe(
      resp => {
        console.log(resp);
      }
    );
    this.edit = false;
  }

  editPref(): void{
    if (this.editPrefs){
      this.editPrefs = false;
    }else{
      this.editPrefs = true;
    }
  }

  getUnit(): string{
    if (this.userService.getUserPrefs().metric){
      return('Metric');
    }else{
      return('Imperial');
    }
  }

  setImp(): void{
    const prefs = this.userService.getUserPrefs();
    prefs.metric = false;
    this.userService.updatePrefs(prefs);
    this.user.preferences = JSON.stringify(prefs);
  }

  setMet(): void{
    const prefs = this.userService.getUserPrefs();
    prefs.metric = true;
    this.userService.updatePrefs(prefs);
    this.user.preferences = JSON.stringify(prefs);
  }

}
