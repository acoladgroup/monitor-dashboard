import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'
import { Http, Response } from "@angular/http"
import { DatabaseStatusResponse, BackupStatusResponse, TableSpace } from '../model';
import { environment } from 'environments/environment';
import 'rxjs/add/operator/map'

@Injectable({
  providedIn: 'root'
})
export class DatabaseService {

  constructor(private http: HttpClient) {
   }

  ngOnInit() {
  }

 /* loadDatabaseStatus() :Promise<DatabaseStatusResponse>{
    return this.http.get<DatabaseStatusResponse>(`${environment.api.url}/databasestatus`)
    .toPromise()
    .then(response => response as DatabaseStatusResponse)
    .catch();
  }; */
 /* loadDatabaseStatus() :Observable<DatabaseStatusResponse>{
    return this.http.get<DatabaseStatusResponse>(`${environment.api.url}/databasestatus`);

   
  } */

  loadDatabaseStatus() :Observable<DatabaseStatusResponse>{
    return this.http.get<DatabaseStatusResponse>(`${environment.api.url}/databasestatus`)
    .map(data => {
      return data;
    });;
  }

  loadBackupStatus() :Observable<BackupStatusResponse>{
    return this.http.get<BackupStatusResponse>(`${environment.api.url}/backupstatus`)
    .map(data => {
      return data;
    });;
  }

  loadTableSpace() :Observable<TableSpace[]>{
    return this.http.get<TableSpace[]>(`${environment.api.url}/tablespace`)
    .map(data => {
      return data;
    });;
  }
}
