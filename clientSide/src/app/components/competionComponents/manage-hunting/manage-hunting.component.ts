import {Component, EventEmitter, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {FishDto} from "../../../models/fish-dto.model";
import {FishService} from "../../../services/fish/fish.service";
import {HuntingDto} from "../../../models/hunting-dto.model";

@Component({
  selector: 'app-manage-hunting',
  templateUrl: './manage-hunting.component.html',
  styleUrls: ['./manage-hunting.component.scss']
})
export class ManageHuntingComponent implements OnInit, OnChanges {

  hunting: HuntingDto = {id: 0, competitionCode: '', fishName: '', numberOfFish: 0, memberNum: 0};

  @Output()
  submit: EventEmitter<HuntingDto> = new EventEmitter<HuntingDto>();

  @Output()
  cancel: EventEmitter<string> = new EventEmitter<string>();

  fiches: FishDto[] = [];

  constructor(
    private fishService: FishService
  ) {
  }

  ngOnChanges(changes: SimpleChanges) {
    this.getFishes();
  }

  ngOnInit() {
    this.getFishes();
  }

  getFishes() {
    this.fishService.findAll()
      .subscribe({
        next: (data) => {
          this.fiches = data;
        }
      })
    ;
  }


  onCancel() {
    this.cancel.emit('hunt');
  }

  addHunting() {
    this.submit.emit(this.hunting);
  }
}
