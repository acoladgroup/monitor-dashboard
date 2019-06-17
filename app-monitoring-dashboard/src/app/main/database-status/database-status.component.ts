import { Component, OnInit } from '@angular/core';
import { DatabaseStatusSource } from '../../_datasources/databasestatus.datasource';
import { DatabaseService } from '../../_services/database.service';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-database-status',
  templateUrl: './database-status.component.html',
  styleUrls: ['./database-status.component.css']
})
export class DatabaseStatusComponent implements OnInit {

  dataSource: DatabaseStatusSource;
  private databaseStatusLoadingSubject = new BehaviorSubject<boolean>(true);
  public databaseStatusLoading$ = this.databaseStatusLoadingSubject.asObservable();


  constructor(private databaseService: DatabaseService) {
    this.databaseService.loadDatabaseStatus().subscribe(res => {
      this.dataSource = new DatabaseStatusSource(res["databaseStatusList"]);
      this.databaseStatusLoadingSubject.next(false);
    });;
  }

  ngOnInit(){
  }

  public getColor(status: string): string {
    return "red";
  }
}
