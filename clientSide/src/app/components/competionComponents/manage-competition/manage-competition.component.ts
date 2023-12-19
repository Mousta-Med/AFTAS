import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {CompetitionDto} from "../../../models/competition-dto.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-manage-competition',
  templateUrl: './manage-competition.component.html',
  styleUrls: ['./manage-competition.component.scss']
})
export class ManageCompetitionComponent implements OnChanges {


  date: Date = new Date;

  minDate: Date = new Date;

  @Input()
  competition!: CompetitionDto;

  @Output()
  submit: EventEmitter<CompetitionDto> = new EventEmitter<CompetitionDto>();

  @Output()
  cancel: EventEmitter<void> = new EventEmitter<void>();

  competitionForm: FormGroup = new FormGroup({
    code: new FormControl(''),
    date: new FormControl('', [Validators.required, Validators.min(this.minDate.getDate())]),
    startTime: new FormControl('',[Validators.required]),
    endTime: new FormControl('', [Validators.required]),
    numberOfParticipants: new FormControl('0', [Validators.required, Validators.min(0)]),
    amount: new FormControl('0', [Validators.required, Validators.min(0)]),
    location: new FormControl('', Validators.required)
  });

  ngOnChanges(changes: SimpleChanges): void {
    this.minDate.setDate(this.date.getDate() + 1);
    this.competitionForm.reset();
    if (this.competition) {
      this.competitionForm.setValue({
        code: this.competition.code,
        date: this.competition.date,
        startTime: this.competition.startTime,
        endTime: this.competition.endTime,
        numberOfParticipants: this.competition.numberOfParticipants,
        amount: this.competition.amount,
        location: this.competition.location
      });
    }
  }

  onSubmit() {
    this.competition = this.competitionForm.value;
    this.competition.code = this.createCode(this.competition.date, this.competition.location);
    this.submit.emit(this.competition);
    this.competitionForm.reset();
  }

  createCode(date: string, location: string): string {
    const format = 'dd-MM-yy';
    const locale = 'en-US';
    return location.substring(0, 3) + '-' + formatDate(date, format, locale);
  }


  onCancel() {
    this.cancel.emit();
    this.competitionForm.reset();
  }
}
