import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material';

import {DialogComponent} from '../common/dialog/dialog.component';

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  constructor( public dialog : MatDialog) { }

  modal(title , content){
    let dialogRef = this.dialog.open(DialogComponent, {
      width: '600px',
      data: {
        title : title,
        content : content,
      }
    });
  }

  error(err){
    let dialogRef = this.dialog.open(DialogComponent, {
      width: '600px',
      data: {
        title : "ERROR",
        content : '<strong>'+err.message+'</strong>',
        type : "ERROR"
      }
    });
  }
}
