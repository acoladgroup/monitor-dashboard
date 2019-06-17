import { Component, OnInit, Inject } from '@angular/core';
import {MatDialogRef , MAT_DIALOG_DATA} from '@angular/material';
@Component({
  selector: 'app-my-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {
  constructor(public thisDialogRef: MatDialogRef<DialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    console.log("Error type : " + this.data.type); 
    console.error(this.data);
  }

  onCloseConfirm() {
    this.thisDialogRef.close('Confirm');
  }
  
  onCloseCancel() {
    this.thisDialogRef.close('Cancel');
  }

  onClose(){
    this.thisDialogRef.close();
  }
}
