import {AfterViewInit, Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {UserService} from '../user-components/user/user.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit, AfterViewInit {

  @Input() message: any;

  @Output() output = new EventEmitter();

  sent = false;

  constructor(private userService: UserService) { }

  ngOnInit() {
    console.log(this.message);
    const user = this.userService.getUser();

    if (this.message.from.name.includes(user.username)){
      this.sent = true;
    }

  }


  ngAfterViewInit(): void {

    this.output.emit('loaded');

  }
}
