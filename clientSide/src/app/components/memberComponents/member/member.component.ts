import {Component, OnInit} from '@angular/core';
import {MemberDto} from "../../../models/member-dto.model";
import {MemberService} from "../../../services/member/member.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-member', templateUrl: './member.component.html', styleUrls: ['./member.component.scss']
})
export class MemberComponent implements OnInit {

  title: string = ''

  member!: MemberDto;

  operation: 'create' | 'update' = 'create';

  members!: MemberDto[];

  visible: boolean = false;

  constructor(
    private memberService: MemberService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService
  ) {
  }

  ngOnInit() {
    this.findAllMembers();
  }

  findAllMembers() {
    this.memberService.findAll()
      .subscribe({
        next: (data) => {
          console.log(data);
          this.members = data;
        }
      })
    ;
  }

  save(member: MemberDto) {
    if (member) {
      if (this.operation === 'create') {
        this.memberService.save(member)
          .subscribe({
            next: () => {
              this.findAllMembers();
              this.messageService.add({severity: 'success', summary: 'Success', detail: 'Member Created Successfully'});
              // this.member = {};
            }
          });
      } else if (this.operation === 'update') {
        this.memberService.update(this.member.num, member)
          .subscribe({
            next: () => {
              this.findAllMembers();
              this.messageService.add({severity: 'success', summary: 'Success', detail: 'Member Updated Successfully'});
            }
          });
      }
      this.visible = false;
    }
  }

  deleteMember(member: MemberDto) {
    this.confirmationService.confirm({
      header: 'Delete member',
      message: `Are you sure you want to delete? You can\'t undo this action afterwords`,
      accept: () => {
        this.memberService.delete(member.num)
          .subscribe({
            next: () => {
              this.findAllMembers();
              this.messageService.add({
                severity: 'success', summary: 'Member deleted', detail: `Member was successfully deleted`
              });
            },error:(err) =>{
              console.log(err);
            }
          });
      }
    });
  }


  updateMember(member: MemberDto) {
    this.title = 'Update Member';
    this.operation = 'update';
    this.member = member;
    this.visible = true;
  }

  createMember(){
    this.title = 'New Member';
    this.operation = 'create';
    this.visible = true;
    this.member = { name: '', familyName: '', identityDocument: 'CIN', identityNumber: '0', nationality: '', accessionDate: '', status: 'PENDING', role: 'ROLE_MEMBER'}
  }

  cancel() {
    this.visible = false;

  }

  findMember(query: any) {
    if (query.target.value !== undefined && query.target.value !== ''){
      this.memberService.findByQuery(query.target.value)
        .subscribe({
          next:(data) =>{
            this.members = data;
          }
        })
    }else {
      this.findAllMembers();
    }
  }
}
