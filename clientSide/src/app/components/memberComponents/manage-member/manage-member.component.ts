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
    identityDocument:  new FormControl('', [Validators.required, Validators.minLength(3)]),
    identityNumber:  new FormControl('0',[Validators.required]),
  });

  ngOnChanges(changes: SimpleChanges): void {
    this.memberForm.reset();
    if (this.member){
      this.memberForm.setValue({
        name: this.member.name,
        familyName: this.member.familyName,
        accessionDate: this.member.accessionDate,
        nationality: this.member.nationality,
        identityDocument: this.member.identityDocument,
        identityNumber: this.member.identityNumber,
      });
    }
  }

  onSubmit() {
    this.member = this.memberForm.value;
    this.submit.emit(this.member);
    this.memberForm.reset();
  }

  onCancel() {
    this.cancel.emit();
    this.memberForm.reset();
  }
}
