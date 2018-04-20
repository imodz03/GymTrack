import { Injectable } from '@angular/core';

@Injectable()
export class ConnectionService {

  private connected = false;
  private checkUrl = 'http://localhost:8087/test';
  // private checkUrl = 'https://elliotbrown.me:8080/test';

  constructor() {
    this.check();
  }

  check(){
    const xhr = new XMLHttpRequest();
    return new Promise((resolve, reject) => {
      xhr.onload = () => {
        // Set online status
        this.connected = true;
        resolve(true);
      };
      xhr.onerror = () => {
        // Set online status
        this.connected = false;
        reject(false);
      };
      xhr.open('GET', this.checkUrl, true);
      xhr.send();
    });
  }


}
