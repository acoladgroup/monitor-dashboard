import { Component, OnInit } from '@angular/core';
import { DatabaseService } from '../../_services/database.service';
import { Chart, formatDate } from 'chart.js';
import { TableSpaceSource } from '../../_datasources/tablespace.datasource';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-security',
  templateUrl: './security.component.html',
  styleUrls: ['./security.component.css']
})
export class SecurityComponent implements OnInit {
  backupStatusPie: any;

  //dabasestatus
  statusUp: number;
  statusDown: number;
  statusUnreachable: number;
  statusUnknown: number;
  statusAgentDown: number;
  statusMetricError: number;
  statusBlackout: number; 

  databaseStatus: any;
  dataSource: TableSpaceSource;

  //backupstatus
  backupCompleted: number;
  backupFailed: number;
  backupCompletedWithWarnings: number;
  backupRunning: number;

  private tableSpaceLoadingSubject = new BehaviorSubject<boolean>(true);
  public tableSpaceLoading$ = this.tableSpaceLoadingSubject.asObservable();

  private databaseStatusLoadingSubject = new BehaviorSubject<boolean>(true);
  public databaseStatusLoading$ = this.databaseStatusLoadingSubject.asObservable();

  private backupStatusLoadingSubject = new BehaviorSubject<boolean>(true);
  public backStatusLoading$ = this.backupStatusLoadingSubject.asObservable();


  constructor(private databaseService: DatabaseService) {
    this.databaseService.loadBackupStatus().subscribe(res => {
      this.backupCompleted = res["backupCompleted"];
      this.backupFailed = res["backupFailed"];
      this.backupCompletedWithWarnings = res["backupCompletedWithWarnings"];
      this.backupRunning = res["backupRunning"];

      this.backupStatusPie = new Chart('backupStatusPie', {
        type: 'doughnut',
        data: {
          labels: [
            'Completed',
            'Running',
            'Failed',
            'Completed with Warnings'
          ],
          datasets: [
            {
              label: "Backup Status",
              data: [this.backupCompleted,this.backupRunning, this.backupFailed, this.backupCompletedWithWarnings],
              backgroundColor: ['rgb(0, 128, 0)', 'rgb(253, 253, 18)', 'rgba(255, 0, 0, 0.733)','rgba(255, 179, 37, 0.87)'],
              fill: true
            }
          ]
        }
      });


      this.backupStatusLoadingSubject.next(false);
    });;

    this.databaseService.loadDatabaseStatus().subscribe(res => {
      this.statusUp = res["databaseUp"];
      this.statusDown = res["databaseDown"];
      this.statusUnreachable = res["databaseUnreachable"];
      this.statusAgentDown = res["databaseAgentDown"];
      this.statusUnknown = res["databaseUnknown"];
      this.statusMetricError = res["databaseMetricError"];
      this.statusBlackout = res["databaseBlackout"];

      this.databaseStatus = new Chart('databaseStatus', {
        type: 'doughnut',
        data: {
          labels: [
            'Up',
            'Down',
            'Unreachable',
            'Metric Error',
            'Agent Down',
            'Blackout',
            'Unknown'
          ],
          datasets: [
            {
              label: "Database Status",
              data: [this.statusUp, this.statusDown, this.statusUnreachable,this.statusMetricError,this.statusAgentDown,this.statusBlackout,this.statusUnknown],
              backgroundColor: ['rgb(0, 128, 0)', 'rgba(255, 0, 0, 0.733)', '#C0C0C0','rgba(255, 179, 37, 0.87)', '#660000', '#000000', '#0000FF'],
              fill: true
            }
          ]
        }
      });

      this.databaseStatusLoadingSubject.next(false);

    });;

    this.databaseService.loadTableSpace().subscribe(res => {
      this.dataSource = new TableSpaceSource(res);
      this.tableSpaceLoadingSubject.next(false);
    });;

    
  }

  public getColor(status: string): string {
    return "red";
  }

  public getClass(percentage_used: string): string {
    if (parseFloat(percentage_used) > 95)
      return "red";
    else if (parseFloat(percentage_used) > 90)
      return "yellow";
    
    return "white"
  }

  ngOnInit() {

 

  }

  ngAfterViewInit() {

  }
}
