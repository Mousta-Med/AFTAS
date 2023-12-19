import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CompetitionDto} from "../../../models/competition-dto.model";
import {CompetitionService} from "../../../services/competition/competition.service";
import {RankingDto} from "../../../models/ranking-dto.model";
import {RankingService} from "../../../services/ranking/ranking.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {MemberDto} from "../../../models/member-dto.model";
import {HuntingService} from "../../../services/hunting/hunting.service";
import {HuntingDto} from "../../../models/hunting-dto.model";

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

  hunting: HuntingDto = {id: 0, competitionCode: '', fishName: '', numberOfFish: 0, memberNum: 0};

  sideBarVisible: boolean = false;

  rankings: RankingDto[] = [];

  huntings: HuntingDto[] = [];

  members: MemberDto[] = [];

  currentMember: number | undefined = 0;

  ranking: RankingDto = {rank: 0, score: 0, memberNum: 0, competitionCode: ''};

  title: string = 'Assign Member';

  visible: boolean = false;

  huntingVisible: boolean = false;

  podiumVisible: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private competitionService: CompetitionService,
    private rankingService: RankingService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private huntingService: HuntingService
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
        this.messageService.add({severity: 'error', summary: 'Error', detail: err.error});
      }
    });
    this.visible = false;
  }

  createRanking() {
    this.visible = true;
    this.ranking = {rank: 1, score: 0, memberNum: 0, competitionCode: ''};
  }

  cancel(event: any) {
    this.visible = false;
    if (event !== 'hunt') {
      this.sideBarVisible = false;
    }
    this.huntingVisible = false;
  }

  deleteMember(member: MemberDto | undefined) {
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

  deleteHunting(id: number) {
    this.confirmationService.confirm({
      header: 'Delete competition',
      message: `Are you sure you want to remove this hunt ? You can\'t undo this action afterwords`,
      accept: () => {
        this.huntingService.delete(id)
          .subscribe({
            next: () => {
              this.setupRanking();
              this.getHuntings(this.competition.code, this.currentMember);
              this.messageService.add({
                severity: 'success', summary: 'Hunting removed', detail: `Hunting removed successfully`
              });
            }
          });
      }
    });
  }

  getHuntings(code: string, num: number | undefined) {
    this.huntingService.findMemberHuntings(code, num)
      .subscribe({
        next: (data => {
          this.huntings = data;
        })
      })
  }

  setHunting(num: number | undefined) {
    this.sideBarVisible = true;
    this.getHuntings(this.competition.code, num);
    this.currentMember = num;
  }

  createHunting() {
    this.hunting = {id: 0, competitionCode: this.competition.code, fishName: '', numberOfFish: 0, memberNum: this.currentMember};
    this.huntingVisible = true;
  }

  saveHunting(hunting: HuntingDto) {
    hunting.memberNum = this.currentMember;
    hunting.competitionCode = this.competition.code;
    this.huntingService.save(hunting).subscribe({
      next: (data) => {
        this.setupRanking();
        this.getRankings(this.competition.code);
        this.setHunting(hunting.memberNum);
        this.messageService.add({severity: 'success', summary: 'Success', detail: 'Hunting Added Successfully'});
      }, error: (err) => {
        console.log(err)
        if (err.error.fishName) {
          this.messageService.add({severity: 'error', summary: 'Error', detail: err.error.fishName});
        } else {
          this.messageService.add({severity: 'error', summary: 'Error', detail: err.error});
        }
      }
    })
    this.huntingVisible = false;
  }

  createPodium() {
    this.podiumVisible = true;
  }

  setupRanking(){
    this.rankingService.setupRankings(this.competition.code)
      .subscribe({
      next: () => {
        this.getRankings(this.competition.code);
      }
    })
  }
}
