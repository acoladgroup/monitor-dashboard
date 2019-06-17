import { ErrorHandler, Injectable, Injector } from '@angular/core';
import { DialogService } from './_services/dialog.service';
import { Router, ActivatedRoute } from '@angular/router';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

  constructor(public dialogService : DialogService ) { }

  handleError(error) {
    if (error && error.status == "401"){
      localStorage.removeItem('loggedUser');
      window.location.href="login";
      return;
    }
    const message = error.message ? error.message : error.toString();
    this.dialogService.error(error);
    console.error(error)
  }

}

