import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatabaseBackupStatusComponent } from './database-backup-status.component';

describe('DatabaseBackupStatusComponent', () => {
  let component: DatabaseBackupStatusComponent;
  let fixture: ComponentFixture<DatabaseBackupStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatabaseBackupStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatabaseBackupStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
