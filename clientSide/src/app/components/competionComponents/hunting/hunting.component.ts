import {Component, EventEmitter, Input, Output} from '@angular/core';
import {HuntingDto} from "../../../models/hunting-dto.model";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-hunting',
  templateUrl: './hunting.component.html',
  styleUrls: ['./hunting.component.scss']
})
export class HuntingComponent {

  @Input()
  huntings: HuntingDto[] = [];

  @Output()
  cancel: EventEmitter<void> = new EventEmitter<void>();

  @Output()
  add: EventEmitter<void> = new EventEmitter<void>();



  onCancel() {
    this.cancel.emit();
  }

  addHunting() {
    this.add.emit();
  }
}
