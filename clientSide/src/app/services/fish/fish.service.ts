import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {FishDto} from "../../models/fish-dto.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FishService {
  private readonly fishUrl = `${environment.api.baseUrl}/${environment.api.fishUrl}`;
  constructor(
    private http: HttpClient
  ) { }

  save(fish: FishDto): Observable<FishDto>{
    return this.http.post<FishDto>(this.fishUrl, fish);
  }

  findAll(): Observable<FishDto[]>{
    return this.http.get<FishDto[]>(this.fishUrl);
  }

  find(id: string): Observable<FishDto>{
    return this.http.get<FishDto>(`${this.fishUrl}/${id}`);
  }


  update(id: string, fish: FishDto): Observable<FishDto>{
    return this.http.put<FishDto>(`${this.fishUrl}/${id}`, fish);
  }

  delete(id: string): Observable<void>{
    return this.http.delete<void>(`${this.fishUrl}/${id}`);
  }
}
