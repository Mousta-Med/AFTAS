import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CompetitionDto} from "../../../models/competition-dto.model";
import {CompetitionService} from "../../../services/competition/competition.service";
import {RankingDto} from "../../../models/ranking-dto.model";
import {RankingService} from "../../../services/ranking/ranking.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {MemberDto} from "../../../models/member-dto.model";

@Component({
  selector: 'app-competition-info',
  templateUrl: './competition-info.component.html',
  styleUrls: ['./competition-info.component.scss']
})
export class CompetitionInfoComponent implements OnInit {

  competition: CompetitionDto = {code: '', date: '', startTime: '', endTime: '', location: '', numberOfParticipants: 0, amount:0};

  rankings: RankingDto[] = [];

  members!: MemberDto[];

  // ranking: RankingDto = {rank: 0, score: 0, memberNum: 0, competitionCode: ''};

  title: string = 'Assign Member';

  visible: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private competitionService: CompetitionService,
    private rankingService: RankingService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.getCompetition(params['code']);
      this.getRankings(params['code']);
    });
  }

  getCompetition(code: string){
    this.competitionService.find(code).subscribe({
      next: (data) => {
        this.competition = data;
      },error: (error) => {
        this.router.navigate(['/notFound']);
      }
    });
  }

  getRankings(code: string){
    this.rankingService.findRankingsByCompetitionCode(code).subscribe({
      next: (data) => {
        this.rankings = data;
      }
    });
  }

  save(ranking: RankingDto){
    this.rankingService.save(ranking).subscribe({
      next: (data) => {
        this.ngOnInit();
        this.messageService.add({severity: 'success', summary: 'Success', detail: 'Member Added Successfully'});
      }
    });
    this.visible = false;
  }

  createRanking() {
    this.visible = true;
  }

  cancel() {
    this.visible = false;
  }
}
