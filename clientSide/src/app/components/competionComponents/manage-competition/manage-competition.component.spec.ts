import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageCompetitionComponent } from './manage-competition.component';

describe('ManageCompetitionComponent', () => {
  let component: ManageCompetitionComponent;
  let fixture: ComponentFixture<ManageCompetitionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManageCompetitionComponent]
    });
    fixture = TestBed.createComponent(ManageCompetitionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
