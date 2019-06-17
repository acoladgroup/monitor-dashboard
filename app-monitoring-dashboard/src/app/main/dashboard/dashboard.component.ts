import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../_services/dashboard.service';
import { NetworkInfoDataSource } from '../../_datasources/network.datasource';
import { BehaviorSubject } from 'rxjs';
import { NetworkServiceDatasource } from '../../_datasources/networkservice.datasource';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  
  applicationServicesUp: number;
  applicationServicesDown: number;
  applicationServicesPending: number;

  sharedServicesUp: number;
  sharedServicesDown: number;
  sharedServicesPending: number;
  applicationServicesList:any;
  sharedServicesList:any;
  networkServicesList:any;
  networkInfoList:any;
  //replace with the network datasource
  dataSource: NetworkServiceDatasource;

  private networkInfoLoadingSubject = new BehaviorSubject<boolean>(true);
  public networkInfoLoading$ = this.networkInfoLoadingSubject.asObservable();

  constructor(private dashboardService: DashboardService) {
    this.dashboardService.loadDashboardData().subscribe(res => {

        this.applicationServicesList = res["applicationServicesList"];
        this.applicationServicesUp = res["applicationServicesUp"];
        this.applicationServicesDown = res["applicationServicesDown"];
        this.applicationServicesPending = res["applicationServicesPending"];

        this.sharedServicesList = res["sharedServicesList"];
        this.sharedServicesUp = res["sharedServicesUp"];
        this.sharedServicesDown = res["sharedServicesDown"];
        this.sharedServicesPending = res["sharedServicesPending"];
        
        this.networkServicesList = res["networkServicesList"];
        this.dataSource = new NetworkServiceDatasource(res["networkServicesList"]);
        this.networkInfoLoadingSubject.next(false);
    });;
  }

  ngOnInit(): void {
   
  } 

}