import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CompetitionDto} from "../../../models/competition-dto.model";

@Component({
  selector: 'app-competition-card',
  templateUrl: './competition-card.component.html',
  styleUrls: ['./competition-card.component.scss']
})
export class CompetitionCardComponent {

  @Input() competition!: CompetitionDto;

  @Output() delete: EventEmitter<CompetitionDto> = new EventEmitter<CompetitionDto>();

  @Output() update: EventEmitter<CompetitionDto> = new EventEmitter<CompetitionDto>();

  ngOnInit() {
  }

  onDelete() {
    this.delete.emit(this.competition);
  }

  onUpdate() {
    this.update.emit(this.competition);
  }
}
