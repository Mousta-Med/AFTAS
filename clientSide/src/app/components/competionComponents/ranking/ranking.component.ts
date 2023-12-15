import {Component, EventEmitter, Input, Output} from '@angular/core';
import {RankingDto} from "../../../models/ranking-dto.model";
import {MemberDto} from "../../../models/member-dto.model";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.scss']
})
export class RankingComponent {

  ranking: RankingDto = {rank: 0, score: 0, memberNum: 0, competitionCode: ''};

  rankingForm : FormGroup = new FormGroup({
    member: new FormControl('0'),
  });

  @Input() members!: MemberDto[];

  @Output() submit: EventEmitter<RankingDto> = new EventEmitter<RankingDto>()
}
