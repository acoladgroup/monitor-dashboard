import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../model';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`${environment.api.url}/connecteduser`)
    .map(user => {
      return user;
    });;
  }  

  getCurrentUserStored(): User {
    let currentUser = JSON.parse(localStorage.getItem('loggedUser'));
    if (currentUser) {
      return currentUser;
    }

    return null;
      
  }  
}
