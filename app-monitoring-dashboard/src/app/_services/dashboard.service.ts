import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'
import { DashboardResponse } from '../model';
import { environment } from 'environments/environment';
import 'rxjs/add/operator/map'

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  
  constructor(private http: HttpClient) {
   }

  ngOnInit() {
  }

  loadDashboardData() :Observable<DashboardResponse>{
    return this.http.get<DashboardResponse>(`${environment.api.url}/dashboard`)
    .map(data => {
      return data;
    });;
  }
}
