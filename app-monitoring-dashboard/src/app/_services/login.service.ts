import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from 'environments/environment';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient ) { }

  login(username: string, password: string) {
    const body = `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}&grant_type=password`;
    const headers = new HttpHeaders({'Content-Type' :'application/x-www-form-urlencoded' , 'Authorization' : 'Basic ' + btoa(environment.api.jwt_client_id+ ':' + environment.api.jwt_client_secret)});

    return this.http.post<any>(environment.api.url+'/oauth/token', body , {headers} ).pipe(map(user => {
      // login successful if there's a jwt token in the response
      if (user) {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('loggedUser', JSON.stringify({username : username , token : user.access_token,  refresh_token : user.refresh_token  }));
      }
      return user;
    }));

  }

  logout() {
    return this.http.get<any>(environment.api.url+'/logout/').pipe(map(user => {
      localStorage.removeItem('loggedUser');
    }));
  }

  refreshToken() {
    let currentUser = JSON.parse(localStorage.getItem('loggedUser'));
    let body = null;
    if (currentUser) {
      const refresh_token = currentUser.refresh_token;
      body = `grant_type=refresh_token&refresh_token=`+refresh_token;
      const headers = new HttpHeaders({'Content-Type' :'application/x-www-form-urlencoded' , 'Authorization' : 'Basic ' + btoa(environment.api.jwt_client_id+ ':' + environment.api.jwt_client_secret)});
  
      return this.http.post<any>(environment.api.url+'/oauth/token', body , {headers} ).pipe(map(user => {
        // login successful if there's a jwt token in the response
        
        return user;
      }));
    }
  }

  getAuthToken() : string {
    let currentUser = JSON.parse(localStorage.getItem('loggedUser'));
 
    if(currentUser != null) {
      return currentUser.token;
    }
 
    return '';
  }
}
