import {Component, OnInit} from '@angular/core';
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

  page: number = 0;

  rows: number = 4;

  filter: string = 'null';

  filterOptions: any[] = [
    {icon: 'pi pi-check', value: 'passed', name: 'Passed'},
    {icon: 'pi pi-play', value: 'ongoing', name: 'On Going'},
    {icon: 'pi pi-calendar', value: 'upcoming', name: 'Upcoming'}
  ];

  constructor(private competitionService: CompetitionService, private confirmationService: ConfirmationService, private messageService: MessageService) {
  }

  filterChanged(){
    this.first = 0;
    this.page = 0;
    this.findAllCompetitions();
  }


  onPageChange(event: any) {
    this.first = event.first;
    this.page = event.page;
    this.rows = event.rows;
    this.findAllCompetitions();
  }

  ngOnInit() {
    this.findAllCompetitions();
  }

  findAllCompetitions() {
    this.competitionService.findAllFilteredAndPaginated(this.filter,this.page, this.rows)
      .subscribe({
        next: (data) => {
          this.competitions = data.content;
          this.length = data.totalElements;
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
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Competition Created Successfully'
              });
              this.competition = {
                code: '',
                location: '',
                endTime: '',
                startTime: '',
                date: '',
                numberOfParticipants: 0,
                amount: 0
              };
            }, error: (err) => {
              console.log(err)
              this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: err.error?.error || err.error?.code
              });
            }
          });
      } else if (this.operation === 'update') {
        this.competitionService.update(this.competition.code, competition)
          .subscribe({
            next: () => {
              this.findAllCompetitions();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Competition Updated Successfully'
              });
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
              if(this.competitions.length === 0){
                this.first = 0
                this.page = 0
              }
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
