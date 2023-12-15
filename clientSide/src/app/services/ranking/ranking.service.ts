import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {RankingDto} from "../../models/ranking-dto.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RankingService {
  private readonly rankingUrl = `${environment.api.baseUrl}/${environment.api.rankingUrl}`;
  constructor(
    private http: HttpClient
  ) { }

  save(ranking: RankingDto): Observable<RankingDto>{
    return this.http.post<RankingDto>(this.rankingUrl, ranking);
  }

  findRankingsByCompetitionCode(code: string): Observable<RankingDto[]>{
    return this.http.get<RankingDto[]>(`${this.rankingUrl}/${code}`);
  }

  findAll(): Observable<RankingDto[]>{
    return this.http.get<RankingDto[]>(this.rankingUrl);
  }

  find(id: number): Observable<RankingDto>{
    return this.http.get<RankingDto>(`${this.rankingUrl}/${id}`);
  }


  update(id: number | undefined, ranking: RankingDto): Observable<RankingDto>{
    return this.http.put<RankingDto>(`${this.rankingUrl}/${id}`, ranking);
  }

  delete(id: number): Observable<void>{
    return this.http.delete<void>(`${this.rankingUrl}/${id}`);
  }
}
