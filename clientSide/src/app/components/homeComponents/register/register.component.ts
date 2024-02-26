import {Component, SimpleChanges} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../../services/authentication/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  errMsg: string = '';
  constructor(
    private router: Router,
    private authService: AuthenticationService) {
  }

  registerForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    status: new FormControl('PENDING', ),
    username: new FormControl('', [Validators.required]),
    familyName:  new FormControl('',[Validators.required]),
    accessionDate:  new FormControl('',[Validators.required]),
    nationality:  new FormControl('', [Validators.required]),
    identityDocument:  new FormControl('', [Validators.required, Validators.minLength(3)]),
    role:  new FormControl('', [Validators.required, Validators.minLength(3)]),
    identityNumber:  new FormControl('0',[Validators.required]),
  });


  register() {
    this.authService.register(this.registerForm.value)
      .subscribe({
        next: (authenticationResponse) => {
          if(authenticationResponse.userRespDto.status === 'PENDING'){
            alert("Please wait util we confirm your account")
            this.router.navigate([''])
          }else {
            localStorage.setItem('user', JSON.stringify(authenticationResponse))
            this.router.navigate([''])
          }
        },
        error: (error) => {
          this.errMsg = error.error.error;
        }
      })
    this.registerForm.reset();
  }


}
