import { CollectionViewer, DataSource } from "@angular/cdk/collections";
import { Observable, BehaviorSubject, of } from 'rxjs';
import { TableSpace, NetworkInfo } from '../model';

export class NetworkInfoDataSource extends DataSource<NetworkInfo> {
  private networkInfoSubject = new BehaviorSubject<NetworkInfo[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingSubject.asObservable();
  public nbElement: number = 0;

  constructor(private networkInfoList: NetworkInfo[]) {
    super();
    this.networkInfoSubject.next(networkInfoList);
  }

  connect(collectionViewer: CollectionViewer): Observable<NetworkInfo[]> {
    return this.networkInfoSubject.asObservable();
  }

  loadStatus() {
    
  }

  disconnect(collectionViewer: CollectionViewer) {
    this.networkInfoSubject.complete();
    this.loadingSubject.complete();
  }
}
