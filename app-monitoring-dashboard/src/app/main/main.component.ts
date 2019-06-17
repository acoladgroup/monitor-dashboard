import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';

import { User } from '../model';
import { LoginService } from '../_services/login.service';
import { UserService } from '../_services/user.service';
import { MatDrawer } from '@angular/material';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  user: User;
  subscription: Subscription; 
  opened: boolean;
  menu: any;
  database: any;
  deploypath = environment.deploypath;
  refreshEnabled: boolean;
  timeoutId: any;
  

  constructor(public route: ActivatedRoute, public router: Router, private loginService: LoginService, private userService: UserService) {
    
  }

  ngOnInit() {
    this.getUserName();
    this.getMenuState();   
    this.getRefreshState();

    if (this.router.url === '/')
      this.router.navigate(["/dashboard"]);

  }

  toggleSideMenu(){
    const state = localStorage.getItem("menuopened");
    if(state) {
      if (state == "true") {
        localStorage.setItem("menuopened","false");
        this.opened = false;
      } else {
        localStorage.setItem("menuopened","true");
        this.opened = true;
      }
    } else {
      localStorage.setItem("menuopened","true");
      this.opened = true;
    }
  }

  toggleRefresh(){
    const state = localStorage.getItem("refreshenable");
    if(state) {
      if (state == "true") {        
        localStorage.setItem("refreshenable","false");
        this.refreshEnabled = false;
        this.cancelRefresh();
      } else {
        localStorage.setItem("refreshenable","true");
        this.refreshEnabled = true;
        this.createRefreshRequest();
      }
    } else {
      localStorage.setItem("refreshenable","true");
      this.refreshEnabled = false;
    }
  }

  getMenuState() {
    const state = localStorage.getItem("menuopened");
    if(state) {
      if (state == "true") {
        this.opened = true;
      } else {
        this.opened = false;
      }
    } else {
      localStorage.setItem("menuopened","true");
      this.opened = true;
    }
  }

  createRefreshRequest() {
    this.timeoutId = setTimeout(function () { 
      location.reload();
    }, 20 * 1000);
  }

  cancelRefresh() {
    clearTimeout(this.timeoutId);
  }

  getRefreshState() {
    const state = localStorage.getItem("refreshenable");
    if(state) {
      if (state == "true") {
        this.refreshEnabled = true;
        this.createRefreshRequest();
      } else {
        this.refreshEnabled = false;
        this.cancelRefresh();
      }
    } else {
      localStorage.setItem("refreshenable","false");
      this.refreshEnabled = false;
    }
  }
  
  getUserName() {
    this.user = this.userService.getCurrentUserStored();
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
