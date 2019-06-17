import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'
import { Http, Response } from "@angular/http"
import { NetworkInfoResponse } from '../model';
import { environment } from 'environments/environment';
import 'rxjs/add/operator/map'

@Injectable({
  providedIn: 'root'
})
export class NetworkService {

  constructor(private http: HttpClient) {
   }

  ngOnInit() {
  }

  loadNetworkInfo() :Observable<NetworkInfoResponse>{
    return this.http.get<NetworkInfoResponse>(`${environment.api.url}/network`)
    .map(data => {
      return data;
    });;
  }
}
