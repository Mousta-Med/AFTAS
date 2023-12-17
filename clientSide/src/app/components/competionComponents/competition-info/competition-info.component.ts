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

  competition: CompetitionDto = {
    code: '',
    date: '',
    startTime: '',
    endTime: '',
    location: '',
    numberOfParticipants: 0,
    amount: 0
  };

  sideBarVisible: boolean = false;

  rankings: RankingDto[] = [];

  members: MemberDto[] = [];

  ranking: RankingDto = {rank: 0, score: 0, memberNum: 0, competitionCode: ''};

  title: string = 'Assign Member';

  visible: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private competitionService: CompetitionService,
    private rankingService: RankingService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.getCompetition(params['code']);
      this.getRankings(params['code']);
    });
  }

  getCompetition(code: string) {
    this.competitionService.find(code).subscribe({
      next: (data) => {
        this.competition = data;
      }, error: (error) => {
        this.router.navigate(['/notFound']);
      }
    });
  }


  getRankings(code: string) {
    this.rankingService.findRankingsByCompetitionCode(code).subscribe({
      next: (data) => {
        this.rankings = data;
        this.members = [];
        for (const index in this.rankings) {
          const ranking = this.rankings[index];
          if (ranking.member) {
            this.members.push(ranking.member);
          }
        }
      }
    });
  }



  save(ranking: RankingDto) {
    this.rankingService.save(ranking).subscribe({
      next: (data) => {
        this.route.params.subscribe(params => {
          this.getCompetition(params['code']);
          this.getRankings(params['code']);
        });
        this.messageService.add({severity: 'success', summary: 'Success', detail: 'Member Added Successfully'});
      }, error: (err) => {
        console.log(err)
        this.messageService.add({severity: 'error', summary: 'Error', detail: err.error});
      }
    });
    this.visible = false;
  }

  createRanking() {
    this.visible = true;
    this.ranking = {rank: 1, score: 0, memberNum: 0, competitionCode: ''};
  }

  cancel() {
    this.visible = false;
  }

  delete(member: MemberDto | undefined) {
    this.confirmationService.confirm({
      header: 'Delete competition',
      message: `Are you sure you want to remove this member: ${member?.name} ? You can\'t undo this action afterwords`,
      accept: () => {
        this.rankingService.delete(this.competition.code, member?.num)
          .subscribe({
            next: () => {
              this.route.params.subscribe(params => {
                this.getCompetition(params['code']);
                this.getRankings(params['code']);
              });
              this.messageService.add({
                severity: 'success', summary: 'Member removed', detail: `member removed successfully`
              });
            }
          });
      }
    });
  }
}
