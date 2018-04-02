import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoalsetComponent } from './goalset.component';

describe('GoalsetComponent', () => {
  let component: GoalsetComponent;
  let fixture: ComponentFixture<GoalsetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GoalsetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoalsetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
