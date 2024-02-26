import {inject, Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot} from "@angular/router";
import {AuthenticationResponse} from "../../models/authentication-response.model";
import {JwtHelperService} from "@auth0/angular-jwt";
import {Location} from "@angular/common";

@Injectable({
  providedIn: 'root'
})

class AdminGuard {

  constructor(
    private route: Router,
    private location: Location,
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const storedUser = localStorage.getItem('user')
    if (storedUser) {
      const authResponse: AuthenticationResponse = JSON.parse(storedUser);
      const token: string = authResponse.token;
      if (token) {
        const jwtHelper = new JwtHelperService();
        const isTokenNonExpired = !jwtHelper.isTokenExpired(token);
        if (isTokenNonExpired) {
          return true
        }
      }
    }
    this.route.navigate(['login'])
    return false;
  }

  isSigned(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const storedUser = localStorage.getItem('user')
    if (storedUser) {
      this.location.back();
      return false
    }
    return true;
  }

  isManager(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const storedUser = localStorage.getItem('user')
    if (storedUser) {
      const authResponse: AuthenticationResponse = JSON.parse(storedUser);
      if (authResponse.userRespDto.role === 'ROLE_MANAGER')
      return true
    }
    this.location.back();
    return false;
  } isMember(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const storedUser = localStorage.getItem('user')
    if (storedUser) {
      const authResponse: AuthenticationResponse = JSON.parse(storedUser);
      if (authResponse.userRespDto.role === 'ROLE_MEMBER')
      return true
    }
    this.location.back();
    return false;
  } isJury(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const storedUser = localStorage.getItem('user')
    if (storedUser) {
      const authResponse: AuthenticationResponse = JSON.parse(storedUser);
      if (authResponse.userRespDto.role === 'ROLE_JURY')
      return true
    }
    this.location.back();
    return false;
  }




}

export const AccessGuardService: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
  return inject(AdminGuard).canActivate(route, state);
}

export const IsSigned: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
  return inject(AdminGuard).isSigned(route, state);
}

export const IsManager: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
  return inject(AdminGuard).isManager(route, state);
}
export const IsMember: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
  return inject(AdminGuard).isMember(route, state);
}

export const IsJury: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
  return inject(AdminGuard).isJury(route, state);
}
