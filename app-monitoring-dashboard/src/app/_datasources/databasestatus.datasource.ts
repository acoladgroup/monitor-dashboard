import { CollectionViewer, DataSource } from "@angular/cdk/collections";
import { Observable, BehaviorSubject, of } from 'rxjs';
import { catchError, finalize } from 'rxjs/operators';

import { DatabaseStatus } from '../model';

export class DatabaseStatusSource extends DataSource<DatabaseStatus> {
  private tasksSubject = new BehaviorSubject<DatabaseStatus[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingSubject.asObservable();
  public nbElement: number = 0;

  constructor(private databaseStatusList: DatabaseStatus[]) {
    super();
    this.tasksSubject.next(databaseStatusList);
  }

  connect(collectionViewer: CollectionViewer): Observable<DatabaseStatus[]> {
    return this.tasksSubject.asObservable();
  }

  loadStatus() {
    
  }

  disconnect(collectionViewer: CollectionViewer) {
    this.tasksSubject.complete();
    this.loadingSubject.complete();
  }
}
