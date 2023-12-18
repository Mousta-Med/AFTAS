import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MemberDto} from "../../../models/member-dto.model";

@Component({
  selector: 'app-member-table',
  templateUrl: './member-table.component.html',
  styleUrls: ['./member-table.component.scss']
})
export class MemberTableComponent {

  @Input() members: MemberDto[] = [];

  @Output() delete: EventEmitter<MemberDto> = new EventEmitter<MemberDto>();

  @Output() update: EventEmitter<MemberDto> = new EventEmitter<MemberDto>();

  onDelete(member: MemberDto) {
    this.delete.emit(member);
  }

  onUpdate(member: MemberDto) {
    this.update.emit(member);
  }

}
