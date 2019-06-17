import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { LoginService } from '../_services/login.service';
import { DialogService } from '../_services/dialog.service';
import { UserService } from '../_services/user.service';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string
  password: string
  connecting: boolean = false

  deploypath = environment.deploypath;

  constructor(private userService: UserService, private loginService: LoginService, public route: ActivatedRoute, public router: Router, public dialogService: DialogService) { }

  ngOnInit() {
    if (this.isLogged())
       this.router.navigate(["/dashboard"]);
  }

  isLogged (){
    return localStorage.getItem("loggedUser") != null
  }

  login() {
    this.connecting = true;
    return this.loginService.login(this.username, this.password).subscribe(res => {
      this.userService.getCurrentUser().subscribe(res2 => {
        this.connecting = false;
        if (res2) {
          let currentUser = JSON.parse(localStorage.getItem('loggedUser'));
          currentUser.firstName =  res2.firstName;
          currentUser.lastName = res2.lastName;
          localStorage.setItem('loggedUser', JSON.stringify(currentUser));
          let returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
          this.router.navigate(["/dashboard"]);

        }
      },
        (err) => {
          this.connecting = false;
          localStorage.removeItem('loggedUser');
          throw err;
        });;
    },
      (err) => {
        this.connecting = false;
        if (err.status == 403 || err.status == 401 || err.status == 400) {
          this.dialogService.error(new Error("Authentication failed, please check your credentials"));
        } else {
          throw err;
        }
      }
    )
  }
}

