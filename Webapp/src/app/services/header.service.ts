import { Injectable } from '@angular/core';
import {HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {UserService} from '../user-components/user/user.service';
import {ConnectionService} from '../connection.service';

@Injectable()
export class HeaderService implements HttpInterceptor{

  constructor(private userService: UserService,
              private connectionS: ConnectionService,
              private http: HttpClient) { }

  // intercepts http requests and appends the users token if it is present
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const user = this.userService.getUser();

    if (user != null){
      req = req.clone({
        setHeaders: {
          userToken: user.token
        }
      });
    }

    this.connectionS.check().catch(() => {

      let storedReq = JSON.parse(localStorage.getItem('storedReqs'));

      if (storedReq === null || storedReq === undefined){
        storedReq = new Array();
      }

      const url = req.url;

      if (req.method === 'POST' && !url.includes('auth/verify')){
        storedReq.push(req);
        alert('Update added to que');
      }

      localStorage.setItem('storedReqs', JSON.stringify(storedReq));

    });

    return next.handle(req);
  }

  // sends http posts on reconnect
  executeUpdate(): void{
    const storedReqs = JSON.parse(localStorage.getItem('storedReqs'));

    if (storedReqs !== null && storedReqs !== undefined && storedReqs.length > 0){

      for (let i = 0; i < storedReqs.length; i++){

        const url = storedReqs[i].url;
        const body = storedReqs[i].body;

        if (url.includes('auth/verify') === false){

          this.http.post(url, body).subscribe(
            resp => {
              storedReqs.splice(i, 1);
              localStorage.setItem('storedReqs', JSON.stringify(storedReqs));
            }
          );

        }else{
          storedReqs.splice(i, 1);
          localStorage.setItem('storedReqs', JSON.stringify(storedReqs));
        }
      }

    }

  }
}
