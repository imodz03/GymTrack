import { Component, OnInit } from '@angular/core';

import {DirectLine} from 'botframework-directlinejs';
import {UserService} from '../user-components/user/user.service';
import {Message} from './message';

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css']
})
export class ChatbotComponent implements OnInit {

  directline: DirectLine;
  msg: Message;
  RawMessageFeed: Array<any>;

  textInput = '';

  constructor(private userService: UserService) { }

  ngOnInit() {

    this.RawMessageFeed = new Array();
    const user = this.userService.getUser();

    this.directline = new DirectLine({
      secret: 'EguAAjmrbao.cwA.vJA.okB1nuJRRsI5Kdtma4OjmMYK3rjhYVAY6fXewggEg3o'
    });

    this.directline.activity$.subscribe(
      msg => {
        this.handleMessage(msg);
      }
    );

    this.msg = new Message();
    this.msg.from.name = user.username;
    this.msg.token = user.token;

    this.msg.type = 'conversationUpdate';
    console.log(this.msg);
    this.directline.postActivity(this.msg).subscribe(
      id => console.log('Posted activity, assigned ID ', id),
      error => console.log('Error posting activity', error)
    );
    this.msg.type = 'message';

  }

  sendMessage(){
    console.log(this.textInput);
    this.msg.text = this.textInput;
    this.directline.postActivity(this.msg).subscribe(
      id => console.log('Posted activity, assigned ID ', id),
      error => console.log('Error posting activity', error)
    );
    this.textInput = '';
  }

  handleMessage(msg){
    if (msg.type.indexOf('message') === 0 && msg.text !== ''){
      this.RawMessageFeed.push(msg);
    }
  }

  scroll(event){
    const out = document.getElementById('chat-feed');
    out.scrollTop = out.scrollHeight - out.clientHeight;
  }
}
