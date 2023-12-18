import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {MemberDto} from "../../../models/member-dto.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-manage-member',
  templateUrl: './manage-member.component.html',
  styleUrls: ['./manage-member.component.scss']
})
export class ManageMemberComponent implements OnChanges{

  title!: string;

  @Input()
  member!: MemberDto;

  @Output()
  submit: EventEmitter<MemberDto> = new EventEmitter<MemberDto>();

  @Output()
  cancel: EventEmitter<void> = new EventEmitter<void>();

  memberForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required]),
    familyName:  new FormControl('',[Validators.required]),
    accessionDate:  new FormControl('',[Validators.required]),
    nationality:  new FormControl('', [Validators.required]),
    identityDocument:  new FormControl('null', [Validators.required]),
    identityNumber:  new FormControl('0',[Validators.required]),
  });

  ngOnChanges(changes: SimpleChanges): void {
    if (this.member){
      this.memberForm = new FormGroup({
        name: new FormControl(this.member.name),
        familyName:  new FormControl(this.member.familyName),
        accessionDate:  new FormControl(this.member.accessionDate),
        nationality:  new FormControl(this.member.nationality),
        identityDocument:  new FormControl(this.member.identityDocument),
        identityNumber:  new FormControl(this.member.identityNumber),
      });
    }
  }

  onSubmit() {
    this.member = this.memberForm.value;
    this.submit.emit(this.member);
  }

  onCancel() {
    this.cancel.emit();
  }
}
