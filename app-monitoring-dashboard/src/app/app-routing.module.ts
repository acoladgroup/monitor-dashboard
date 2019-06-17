import { NgModule }               from '@angular/core';
import { RouterModule, Routes }   from '@angular/router';
import { DashboardComponent }      from './main/dashboard/dashboard.component';
import { ProfileDetailsComponent}         from './main/profile/profile-details/profile-details.component';
import { LoginComponent}         from './login/login.component';
import { LogoutComponent}         from './login/logout.component';
import { NotfoundComponent }      from './notfound/notfound.component';
import { DatabaseComponent } from './main/database/database.component';
import { MainComponent} from './main/main.component'
import { DatabaseMonitorComponent } from './main/database-monitor/database-monitor.component';
import { DatabaseStatusComponent } from './main/database-status/database-status.component';
import { DatabaseBackupStatusComponent } from './main/database-backup-status/database-backup-status.component';
import { SecurityComponent } from './main/security/security.component';

import {HashLocationStrategy, Location, LocationStrategy} from '@angular/common';

import { AuthGuard } from './_guards/auth.guard';
import { NetworkComponent } from './main/network/network.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: '', component: MainComponent ,canActivate:[AuthGuard], children: [
    { path: 'dashboard', component: DashboardComponent},
    { path: 'database' , component: DatabaseComponent, children: [
      { path: 'database-monitor', component: DatabaseMonitorComponent},
    ]

    },  
    { path: 'database-monitor', component: DatabaseMonitorComponent},
    { path: 'database-status', component: DatabaseStatusComponent },
    { path: 'database-backup-status', component: DatabaseBackupStatusComponent },
    { path: 'profile', component: ProfileDetailsComponent },
    { path: 'security', component: SecurityComponent },
    { path: 'network', component: NetworkComponent },
  
    { path: '**', component: NotfoundComponent  },
  ]},
];

@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes,{ useHash: true })],

})
export class AppRoutingModule {}
