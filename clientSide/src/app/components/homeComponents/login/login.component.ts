import { Component } from '@angular/core';
import {AuthenticationRequest} from "../../../models/authentication-request.model";
import {AuthenticationService} from "../../../services/authentication/authentication.service";

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
  ) {
  }

  login() {
    this.errMsg = '';
    this.authenticationService.login(this.authenticationRequest)
      .subscribe({
        next: (authenticationResponse)=>{
          console.log(authenticationResponse);
        },
        error: (error) => {
          this.errMsg = error.error.error;
        }
      })
  }
}
