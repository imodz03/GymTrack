import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-simple-dialog',
  templateUrl: './simple-dialog.component.html',
  styleUrls: ['./simple-dialog.component.css']
})
export class SimpleDialogComponent implements OnInit {

  msg = 'Simple dialog comp';
  options;

  constructor(public dialogRef: MatDialogRef<SimpleDialogComponent>,
              @Inject(MAT_DIALOG_DATA)data: any) {
    this.msg = data.msg;
    this.options = data.options;
  }

  ngOnInit() {
  }

  feedback(val): void{

    this.dialogRef.close(val);

  }

}
