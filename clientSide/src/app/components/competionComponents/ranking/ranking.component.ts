import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {RankingDto} from "../../../models/ranking-dto.model";
import {MemberDto} from "../../../models/member-dto.model";
import {MemberService} from "../../../services/member/member.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.scss']
})
export class RankingComponent implements  OnChanges{

  members: MemberDto[] = [];

  @Input() ranking: RankingDto = {rank: 1, score: 0, memberNum: 0, competitionCode: ''};

  @Input() competitionMembers: MemberDto[] = [];

  @Output() submit: EventEmitter<RankingDto> = new EventEmitter<RankingDto>();

  @Output() cancel: EventEmitter<void> = new EventEmitter<void>();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private memberService: MemberService
  ) {
  }

  ngOnChanges(changes: SimpleChanges) {
    this.getMembers();
    this.route.params.subscribe(params => {
      this.ranking.competitionCode = params['code'];
    });
  }

  // ngOnInit() {
  //   this.getMembers();
  //   this.route.params.subscribe(params => {
  //     this.ranking.competitionCode = params['code'];
  //   });
  // }

  getMembers(){
    this.memberService.findAll()
      .subscribe({
        next: (data) => {
          this.members = data.filter(member => !this.competitionMembers.some(compMember => compMember.num === member.num));
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
    this.ranking = {rank: 1, score: 0, memberNum: 0, competitionCode: ''};
  }
}
