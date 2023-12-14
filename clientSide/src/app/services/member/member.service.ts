import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {MemberDto} from "../../models/member-dto.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  private readonly memberUrl = `${environment.api.baseUrl}/${environment.api.memberUrl}`;
  constructor(
    private http: HttpClient
  ) { }

  save(member: MemberDto): Observable<MemberDto>{
    return this.http.post<MemberDto>(this.memberUrl, member);
  }

  findAll(): Observable<MemberDto[]>{
    return this.http.get<MemberDto[]>(this.memberUrl);
  }

  find(id: number): Observable<MemberDto>{
    return this.http.get<MemberDto>(`${this.memberUrl}/${id}`);
  }


  update(id: number | undefined, member: MemberDto): Observable<MemberDto>{
    return this.http.put<MemberDto>(`${this.memberUrl}/${id}`, member);
  }

  delete(id: number): Observable<void>{
    return this.http.delete<void>(`${this.memberUrl}/${id}`);
  }
}
