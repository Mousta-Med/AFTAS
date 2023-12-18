import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {HuntingDto} from "../../models/hunting-dto.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HuntingService {
  private readonly huntingUrl = `${environment.api.baseUrl}/${environment.api.huntingUrl}`;
  constructor(
    private http: HttpClient
  ) { }

  save(hunting: HuntingDto): Observable<HuntingDto>{
    return this.http.post<HuntingDto>(this.huntingUrl, hunting);
  }

  findAll(): Observable<HuntingDto[]>{
    return this.http.get<HuntingDto[]>(this.huntingUrl);
  }
  findMemberHuntings(competitionCode: string, memberNum: number | undefined): Observable<HuntingDto[]>{
    return this.http.get<HuntingDto[]>(`${this.huntingUrl}/${competitionCode}/${memberNum}`);
  }

  find(id: number): Observable<HuntingDto>{
    return this.http.get<HuntingDto>(`${this.huntingUrl}/${id}`);
  }


  update(id: number | undefined, hunting: HuntingDto): Observable<HuntingDto>{
    return this.http.put<HuntingDto>(`${this.huntingUrl}/${id}`, hunting);
  }

  delete(id: number): Observable<void>{
    return this.http.delete<void>(`${this.huntingUrl}/${id}`);
  }
}
