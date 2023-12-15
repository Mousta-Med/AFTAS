import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {RankingDto} from "../../../models/ranking-dto.model";
import {MemberDto} from "../../../models/member-dto.model";
import {MemberService} from "../../../services/member/member.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CompetitionDto} from "../../../models/competition-dto.model";

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.scss']
})
export class RankingComponent implements OnInit{

  members!: MemberDto[];

  ranking: RankingDto = {rank: 1, score: 0, memberNum: 0, competitionCode: ''};

  @Output() submit: EventEmitter<RankingDto> = new EventEmitter<RankingDto>();

  @Output() cancel: EventEmitter<void> = new EventEmitter<void>();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private memberService: MemberService
  ) {
  }

  ngOnInit() {
    this.getMembers();
    this.route.params.subscribe(params => {
      this.ranking.competitionCode = params['code'];
    });
  }

  getMembers(){
    this.memberService.findAll()
      .subscribe({
        next: (data) => {
          this.members = data;
        }
      })
    ;
  }

  isMemberNumValid(): boolean {
    return this.ranking.memberNum !== undefined && this.ranking.memberNum !== null && this.ranking.memberNum !== 0;
  }


  onSubmit(){
    this.submit.emit(this.ranking);
  }

  onCancel(){
    this.cancel.emit();
  }
}
