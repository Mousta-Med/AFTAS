import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CompetitionDto} from "../../../models/competition-dto.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-competition-card',
  templateUrl: './competition-card.component.html',
  styleUrls: ['./competition-card.component.scss']
})
export class CompetitionCardComponent {

  @Input() competition!: CompetitionDto;

  @Output() delete: EventEmitter<CompetitionDto> = new EventEmitter<CompetitionDto>();

  @Output() update: EventEmitter<CompetitionDto> = new EventEmitter<CompetitionDto>();

  constructor(
    private router: Router
  ) {
  }

  ngOnInit() {
  }

  onDelete() {
    this.delete.emit(this.competition);
  }

  onUpdate() {
    this.update.emit(this.competition);
  }

  enter() {
    this.router.navigate([`/competition/${this.competition.code}`]);
  }
}
