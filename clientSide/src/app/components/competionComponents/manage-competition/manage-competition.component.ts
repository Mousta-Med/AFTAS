import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {CompetitionDto} from "../../../models/competition-dto.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-manage-competition',
  templateUrl: './manage-competition.component.html',
  styleUrls: ['./manage-competition.component.scss']
})
export class ManageCompetitionComponent implements OnInit, OnChanges {


  date: Date = new Date;

  @Input()
  competition!: CompetitionDto;

  @Output()
  submit: EventEmitter<CompetitionDto> = new EventEmitter<CompetitionDto>();

  @Output()
  cancel: EventEmitter<void> = new EventEmitter<void>();

  competitionForm: FormGroup = new FormGroup({
    code: new FormControl(''),
    date: new FormControl('', [Validators.required, Validators.min(this.date.getDate())]),
    startTime:  new FormControl('',[Validators.required]),
    endTime:  new FormControl('', [Validators.required]),
    numberOfParticipants:  new FormControl('0', [Validators.required, Validators.min(0)]),
    amount:  new FormControl('0',[Validators.required, Validators.min(0)]),
    location:  new FormControl('', Validators.required)
  });

  ngOnChanges(changes: SimpleChanges): void {
    if (this.competition){
      this.competitionForm = new FormGroup({
        date: new FormControl(this.competition.date),
        startTime:  new FormControl(this.competition.startTime),
        endTime:  new FormControl(this.competition.endTime),
        numberOfParticipants:  new FormControl(this.competition.numberOfParticipants),
        amount:  new FormControl(this.competition.amount),
        location:  new FormControl(this.competition.location)
      });
    }
  }

  ngOnInit(): void {
  }

  isCompetitionValid(): boolean {
    return this.hasLength(this.competition.startTime)
  }

  private hasLength(input: string | undefined): boolean {
    return input !== null && input !== undefined && input.length > 0
  }

  onSubmit() {
    this.competition = this.competitionForm.value;
    this.competition.code = this.createCode(this.competition.date, this.competition.location);
    this.submit.emit(this.competition);
  }

  createCode(date: string, location: string): string {
    const format = 'dd-MM-yy';
    const locale = 'en-US';
    return location.substring(0, 3) + '-' + formatDate(date, format, locale);
  }


  onCancel() {
    this.cancel.emit();
  }
}
