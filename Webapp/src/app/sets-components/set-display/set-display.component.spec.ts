import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SetDisplayComponent } from './set-display.component';

describe('SetDisplayComponent', () => {
  let component: SetDisplayComponent;
  let fixture: ComponentFixture<SetDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SetDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SetDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
