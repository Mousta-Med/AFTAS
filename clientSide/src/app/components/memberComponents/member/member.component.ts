import {Component, OnInit} from '@angular/core';
import {MemberDto} from "../../../models/member-dto.model";
import {MemberService} from "../../../services/member/member.service";

@Component({
  selector: 'app-member', templateUrl: './member.component.html', styleUrls: ['./member.component.scss']
})
export class MemberComponent implements OnInit {

  sidebarVisible: boolean = false;

  member!: MemberDto;

  operation: 'create' | 'update' = 'create';

  members!: MemberDto[];
  visible: boolean =false;

  constructor(private memberService: MemberService) {
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
              // this.messageService.add({severity: 'success', summary: 'Success', detail: 'Member Created Successfully'});
              // this.member = {};
            }
          });
      } else if (this.operation === 'update') {
        this.memberService.update(this.member.num, member)
          .subscribe({
            next: () => {
              this.findAllMembers();
              // this.messageService.add({severity: 'success', summary: 'Success', detail: 'Member Updated Successfully'});
            }
          });
      }
      this.sidebarVisible = false;
    }
  }

  createMember(){
    this.visible = true;
    this.sidebarVisible = true;
  }

  cancel() {
    this.sidebarVisible = false;
  }
}
