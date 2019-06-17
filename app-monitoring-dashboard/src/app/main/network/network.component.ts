import { Component, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { NetworkInfoDataSource } from '../../_datasources/network.datasource';
import { NetworkService } from '../../_services/network.service';

@Component({
  selector: 'app-network',
  templateUrl: './network.component.html',
  styleUrls: ['./network.component.css']
})
export class NetworkComponent implements OnInit {
   
  dataSource: NetworkInfoDataSource;

  private networkInfoLoadingSubject = new BehaviorSubject<boolean>(true);
  public networkInfoLoading$ = this.networkInfoLoadingSubject.asObservable();


  constructor(private networkService: NetworkService) {
    this.networkService.loadNetworkInfo().subscribe(res => {
      this.dataSource = new NetworkInfoDataSource(res["networkInfoList"]);
      this.networkInfoLoadingSubject.next(false);
  });;
    
  }

  ngOnInit() {

  }

  ngAfterViewInit() {

  }
}
