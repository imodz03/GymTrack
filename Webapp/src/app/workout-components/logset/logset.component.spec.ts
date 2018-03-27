import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogsetComponent } from './logset.component';

describe('LogsetComponent', () => {
  let component: LogsetComponent;
  let fixture: ComponentFixture<LogsetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogsetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogsetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
