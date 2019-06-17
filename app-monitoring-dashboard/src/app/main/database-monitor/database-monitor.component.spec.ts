import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatabaseMonitorComponent } from './database-monitor.component';

describe('DatabaseMonitorComponent', () => {
  let component: DatabaseMonitorComponent;
  let fixture: ComponentFixture<DatabaseMonitorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatabaseMonitorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatabaseMonitorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
