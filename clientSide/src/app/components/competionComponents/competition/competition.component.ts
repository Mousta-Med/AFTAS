import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {CompetitionDto} from "../../../models/competition-dto.model";
import {CompetitionService} from "../../../services/competition/competition.service";
import {ConfirmationService, MessageService} from "primeng/api";


@Component({
  selector: 'app-competition', templateUrl: './competition.component.html', styleUrls: ['./competition.component.scss']
})
export class CompetitionComponent implements OnInit {

  title: string = '';

  competition!: CompetitionDto;

  operation: 'create' | 'update' = 'create';

  competitions!: CompetitionDto[];

  length: number = 0;

  visible: boolean = false;

  first: number = 0;

  rows: number = 10;

  onPageChange(event: any) {
    this.first = event.first;
    this.rows = event.rows;
  }

  value: any;

  filterOptions: any[] = [
    { icon: 'pi pi-check', value: 'passed', name: 'Passed' },
    { icon: 'pi pi-play', value: 'ongoing', name: 'On Going' },
    { icon: 'pi pi-calendar', value: 'upcoming', name: 'Upcoming' }
  ];

  constructor(private competitionService: CompetitionService, private confirmationService: ConfirmationService, private messageService: MessageService) {
  }



  ngOnInit() {
    this.findAllCompetitions();
  }


  findAllCompetitions() {
    this.competitionService.findAll()
      .subscribe({
        next: (data) => {
          this.competitions = data;
          this.length = this.competitions.length;
        }
      });
  }

  save(competition: CompetitionDto) {
    if (competition) {
      if (this.operation === 'create') {
        this.competitionService.save(competition)
          .subscribe({
            next: () => {
              this.findAllCompetitions();
              this.messageService.add({severity: 'success', summary: 'Success', detail: 'Competition Created Successfully'});
              this.competition = {
                code: '',
                location: '',
                endTime: '',
                startTime: '',
                date: '',
                numberOfParticipants: 0,
                amount: 0
              };
            }
          });
      } else if (this.operation === 'update') {
        this.competitionService.update(this.competition.code, competition)
          .subscribe({
            next: () => {
              this.findAllCompetitions();
              this.messageService.add({severity: 'success', summary: 'Success', detail: 'Competition Updated Successfully'});
            }
          });
      }
      this.visible = false;
    }
  }

  deleteCompetition(competition: CompetitionDto) {
    this.confirmationService.confirm({
      header: 'Delete competition',
      message: `Are you sure you want to delete? You can\'t undo this action afterwords`,
      accept: () => {
        this.competitionService.delete(competition.code)
          .subscribe({
            next: () => {
              this.findAllCompetitions();
              this.messageService.add({
                severity: 'success', summary: 'Competition deleted', detail: `Competition was successfully deleted`
              });
            }
          });
      }
    });
  }


  updateCompetition(competition: CompetitionDto) {
    this.title = 'Update Competition';
    this.operation = 'update';
    this.competition = competition;
    this.visible = true;
  }

  createCompetition() {
    this.title = 'New Competition';
    this.operation = 'create';
    this.visible = true;
    this.competition = {
      code: '',
      location: '',
      endTime: '',
      startTime: '',
      date: '',
      numberOfParticipants: 0,
      amount: 0
    };
  }

  cancel() {
    this.visible = false;
  }
}
