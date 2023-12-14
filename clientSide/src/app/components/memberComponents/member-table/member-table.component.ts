import {Component, Input} from '@angular/core';
import {MemberDto} from "../../../models/member-dto.model";

@Component({
  selector: 'app-member-table',
  templateUrl: './member-table.component.html',
  styleUrls: ['./member-table.component.scss']
})
export class MemberTableComponent {

  @Input() members: MemberDto[] = [];

}
