import { CollectionViewer, DataSource } from "@angular/cdk/collections";
import { Observable, BehaviorSubject, of } from 'rxjs';
import { TableSpace } from 'app/model';

export class TableSpaceSource extends DataSource<TableSpace> {
  private tablespaceSubject = new BehaviorSubject<TableSpace[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingSubject.asObservable();
  public nbElement: number = 0;

  constructor(private tableSpaceList: TableSpace[]) {
    super();
    this.tablespaceSubject.next(tableSpaceList);
  }

  connect(collectionViewer: CollectionViewer): Observable<TableSpace[]> {
    return this.tablespaceSubject.asObservable();
  }

  loadStatus() {
    
  }

  disconnect(collectionViewer: CollectionViewer) {
    this.tablespaceSubject.complete();
    this.loadingSubject.complete();
  }
}
