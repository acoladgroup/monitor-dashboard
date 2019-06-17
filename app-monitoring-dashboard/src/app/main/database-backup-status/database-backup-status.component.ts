import { Component, OnInit } from '@angular/core';
import { BackupStatusSource } from '../../_datasources/backupstatus.datasource';
import { DatabaseService } from '../../_services/database.service';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-database-backup-status',
  templateUrl: './database-backup-status.component.html',
  styleUrls: ['./database-backup-status.component.css']
})
export class DatabaseBackupStatusComponent implements OnInit {

  backupSource: BackupStatusSource;
  private backupStatusLoadingSubject = new BehaviorSubject<boolean>(true);
  public backStatusLoading$ = this.backupStatusLoadingSubject.asObservable();

  constructor(private databaseService: DatabaseService) {
    this.databaseService.loadBackupStatus().subscribe(res => {
      this.backupSource = new BackupStatusSource(res["backupStatusList"]);
      this.backupStatusLoadingSubject.next(false);
    });;
  }

  ngOnInit(){
  }
}
