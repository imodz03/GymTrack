import {Injectable} from '@angular/core';
import {
  ActivatedRoute, ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router,
  RouterStateSnapshot
} from '@angular/router';
import {UserService} from './user-components/user/user.service';
import {register} from 'ts-node';

@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild{

  constructor(private userService: UserService,
              private router: Router){  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){
    const user = this.userService.getUser();
    const path = state.url;
    if (user == null){
      if (path === '/login' || path === '/register'){
        return true;
      }else{
        this.router.navigate(['/login']);
      }
    }else{
      return true;
    }
  }


  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return false;
  }
}
