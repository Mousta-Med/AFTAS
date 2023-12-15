import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {MemberDto} from "../../../models/member-dto.model";

@Component({
  selector: 'app-manage-member',
  templateUrl: './manage-member.component.html',
  styleUrls: ['./manage-member.component.scss']
})
export class ManageMemberComponent implements OnChanges{

  title!: string;

  @Input()
  member!: MemberDto;

  @Input()
  operation: 'create' | 'update' = 'create'

  @Output()
  submit: EventEmitter<MemberDto> = new EventEmitter<MemberDto>();

  @Output()
  cancel: EventEmitter<void> = new EventEmitter<void>();

  ngOnChanges(changes: SimpleChanges): void {
    if (this.operation === 'update') {
      this.title = 'Update Member';
    } else {
      this.title = 'New Member';
    }
  }

  get ismemberValid(): boolean {
    return this.hasLength(this.member.name)
  }

  private hasLength(input: string | undefined): boolean {
    return input !== null && input !== undefined && input.length > 0
  }

  onSubmit() {
    this.submit.emit(this.member);
  }

  onCancel() {
    this.cancel.emit();
  }
}
