import { CollectionViewer, DataSource } from "@angular/cdk/collections";
import { Observable, BehaviorSubject, of } from 'rxjs';
import { BackupStatus } from '../model';

export class BackupStatusSource extends DataSource<BackupStatus> {
  private backupSubject = new BehaviorSubject<BackupStatus[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingSubject.asObservable();
  public nbElement: number = 0;

  constructor(private backupStatusList: BackupStatus[]) {
    super();
    this.backupSubject.next(backupStatusList);
  }

  connect(collectionViewer: CollectionViewer): Observable<BackupStatus[]> {
    return this.backupSubject.asObservable();
  }

  loadStatus() {
    
  }

  disconnect(collectionViewer: CollectionViewer) {
    this.backupSubject.complete();
    this.loadingSubject.complete();
  }
}
