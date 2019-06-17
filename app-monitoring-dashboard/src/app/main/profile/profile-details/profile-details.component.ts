import { Component, OnInit  } from '@angular/core';
import { BehaviorSubject } from 'rxjs'


import { ProfileService } from '../../../_services/profile.service';
import { Profile } from '../../../model';

@Component({
  selector: 'app-profile-details',
  templateUrl: './profile-details.component.html',
  styleUrls: ['./profile-details.component.css']
})
export class ProfileDetailsComponent implements OnInit {
  profile : Profile;
  private loadingSubject = new BehaviorSubject<boolean>(true);
  public loading$ = this.loadingSubject.asObservable();

  constructor(private profileService: ProfileService) { }

  ngOnInit(){
    this.getProfile();
  }

  getProfile() {
	  this.profileService.getProfile().subscribe(
	      data => {
          this.profile = data;
          this.loadingSubject.next(false);
	      },
        error => {
          throw error
        }
	  );
   }
}

