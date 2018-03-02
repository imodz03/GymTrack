import { Injectable } from '@angular/core';
import {HttpRequest, HttpHandler, HttpEvent, HttpInterceptor} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {UserService} from '../user/user.service';

@Injectable()
export class HeaderService implements HttpInterceptor{

  constructor(private userService: UserService) { }

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
    return next.handle(req);
  }
}
