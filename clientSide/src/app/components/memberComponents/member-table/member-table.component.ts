import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MemberDto} from "../../../models/member-dto.model";
import {MemberService} from "../../../services/member/member.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-member-table',
  templateUrl: './member-table.component.html',
  styleUrls: ['./member-table.component.scss']
})
export class MemberTableComponent {

  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private userService: MemberService,
  ) {
  }

  @Input() members: MemberDto[] = [];

  @Output() delete: EventEmitter<MemberDto> = new EventEmitter<MemberDto>();

  @Output() update: EventEmitter<MemberDto> = new EventEmitter<MemberDto>();

  onDelete(member: MemberDto) {
    this.delete.emit(member);
  }

  onUpdate(member: MemberDto) {
    this.update.emit(member);
  }

  onCheck(id: number){
    this.confirmationService.confirm({
      header: 'Accept Account',
      message: `Are you sure you want to Valid this account? You can\'t undo this action afterwords`,
      accept: () => {
        this.userService.changeStatus('ACCEPTED',id)
          .subscribe({
            next: () => {
              // this.findAllMembers();
              this.messageService.add({
                severity: 'success', summary: 'Account Accepted', detail: `Account was successfully Accepted`
              });
            },error:(err) =>{
              console.log(err);
            }
          });
      }
    });
  }
  onRefuse(id: number){
    this.confirmationService.confirm({
      header: 'Refuse Account',
      message: `Are you sure you want to Refuse this account? You can\'t undo this action afterwords`,
      accept: () => {
        this.userService.changeStatus('REFUSED',id)
          .subscribe({
            next: () => {
              // this.findAllMembers();
              this.messageService.add({
                severity: 'success', summary: 'Account Refused', detail: `Account was successfully Refused`
              });
            },error:(err) =>{
              console.log(err);
            }
          });
      }
    });
  }

}
