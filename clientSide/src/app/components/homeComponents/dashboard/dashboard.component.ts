import {Component, OnInit} from '@angular/core';
import {MemberService} from "../../../services/member/member.service";
import {FishService} from "../../../services/fish/fish.service";
import {HuntingService} from "../../../services/hunting/hunting.service";
import {CompetitionService} from "../../../services/competition/competition.service";
import {MemberDto} from "../../../models/member-dto.model";
import {CompetitionDto} from "../../../models/competition-dto.model";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit{

  fishLength: number = 0;
  memberLength: number = 0;
  competitionLength: number = 0;
  huntingLength: number = 0;

  members: MemberDto[] = [];
  competitions: CompetitionDto[] = [];

  constructor(
    private memberService: MemberService,
    private fishService: FishService,
    private huntingService: HuntingService,
    private competitionService: CompetitionService
  ) {
  }

  ngOnInit() {
    this.fishService.findAll().subscribe({next:(data)=>{this.fishLength = data.length}});
    this.memberService.findAll().subscribe({next:(data)=>{this.memberLength = data.length; this.members = data.slice(0, 6);}});
    this.huntingService.findAll().subscribe({next:(data)=>{this.huntingLength = data.length}});
    this.competitionService.findAll().subscribe({next:(data)=>{this.competitionLength = data.length; this.competitions = data.slice(0, 6);}});
  }
}
