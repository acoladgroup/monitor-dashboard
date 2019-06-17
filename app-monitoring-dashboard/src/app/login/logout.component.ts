import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { LoginService } from '../_services/login.service';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {
  username: string
  password: string
  connecting: boolean = false

  deploypath = environment.deploypath;

  constructor(private loginService: LoginService, public route: ActivatedRoute, public router: Router) { }

  ngOnInit() {
    if (this.isLogged())
      this.logout();
    else
      this.router.navigate(["/dashboard"]);
  }

  isLogged (){
    return localStorage.getItem("loggedUser") != null
  }

  logout (){
    this.loginService.logout().subscribe(
      () =>{
        this.router.navigate(["/login"])
      }
    )
  }
}

