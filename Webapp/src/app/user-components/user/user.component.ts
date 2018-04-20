import { Component, OnInit } from '@angular/core';
import {UserService} from './user.service';
import {Router} from '@angular/router';
import {ConnectionService} from '../../services/connection.service';
import {MatDialog, MatDialogRef, MatSnackBar} from '@angular/material';
import {SimpleDialogComponent} from '../../simple-dialog/simple-dialog.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private userService: UserService,
              private router: Router,
              private connectionS: ConnectionService,
              private dialog: MatDialog,
              private snackbar: MatSnackBar) { }

  user;
  edit = false;
  editPrefs = false;

  online = true;
  dialogRef;

  ngOnInit() {
    this.connectionS.check().then(() => {
      this.userService.getMe().subscribe(
        resp => {
          console.log(resp);
          this.user = resp;
        }
      );
    }).catch(() => {
      this.online = false;
    });
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

  back(): void{
    this.router.navigate(['/myworkouts']);
  }

  delete(): void{
    const options = ['Confirm', 'Cancel'];
    this.dialogRef = this.dialog.open(SimpleDialogComponent,
      {data: {msg: 'Are you sure you want to delete your account, It will not be recoverable', options: options}});

    this.dialogRef.afterClosed().subscribe(
      res => {
        if (res === options[0]){
          this.userService.deleteMyAccount().subscribe(
            resp => {
              if (resp === 1){
                this.userService.logout();
                this.router.navigate(['/login']);
              }else{
                this.snackbar.open('Something went wrong deleting your account please try later',
                  'Dismiss', {duration: 10000});
              }
            }
          );
        }else{
          this.dialogRef.close();
        }
      }
    );
  }

}
