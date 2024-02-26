import { Component } from '@angular/core';
import {AuthenticationRequest} from "../../../models/authentication-request.model";
import {AuthenticationService} from "../../../services/authentication/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  errMsg: string = '';
  authenticationRequest: AuthenticationRequest = {username: '', password: ''};

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
  }

  login() {
    this.errMsg = '';
    this.authenticationService.login(this.authenticationRequest)
      .subscribe({
        next: (authenticationResponse)=>{
          if(authenticationResponse.userRespDto.status === 'PENDING'){
            alert("Your Account Not Confirmed Yet")
            this.router.navigate([''])
          }else if(authenticationResponse.userRespDto.status === 'REFUSED'){
            alert("Your Account Is Refused")
            this.router.navigate([''])
          }else {
            localStorage.setItem('user', JSON.stringify(authenticationResponse))
            if(authenticationResponse.userRespDto.role === 'ROLE_MANAGER'){
            this.router.navigate(['members'])
            }else if(authenticationResponse.userRespDto.role === 'ROLE_MEMBER'){
              this.router.navigate([''])
            }else{
              this.router.navigate(['competitions'])
            }
          }
        },
        error: (error) => {
          this.errMsg = error.error.error;
        }
      })
  }
}
