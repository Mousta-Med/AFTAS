import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageHuntingComponent } from './manage-hunting.component';

describe('ManageHuntingComponent', () => {
  let component: ManageHuntingComponent;
  let fixture: ComponentFixture<ManageHuntingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManageHuntingComponent]
    });
    fixture = TestBed.createComponent(ManageHuntingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
