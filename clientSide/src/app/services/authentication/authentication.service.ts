import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthenticationRequest} from "../../models/authentication-request.model";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {AuthenticationResponse} from "../../models/authentication-response.model";
import {MemberDto} from "../../models/member-dto.model";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly registerUrl = `${environment.api.baseUrl}/${environment.api.registerUrl}`;
  private readonly loginUrl = `${environment.api.baseUrl}/${environment.api.loginUrl}`;

  constructor(
    private http: HttpClient
  ) { }

  login(authRequest: AuthenticationRequest): Observable<AuthenticationResponse>{
    return this.http.post<AuthenticationResponse>(this.loginUrl, authRequest);
  }

  register(user: MemberDto): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(this.registerUrl, user);
  }
}
