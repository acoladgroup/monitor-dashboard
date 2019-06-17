import { CollectionViewer, DataSource } from "@angular/cdk/collections";
import { Observable, BehaviorSubject, of } from 'rxjs';
import { NetworkService } from '../model';

export class NetworkServiceDatasource extends DataSource<NetworkService> {
  private networkServiceSubject = new BehaviorSubject<NetworkService[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingSubject.asObservable();
  public nbElement: number = 0;

  constructor(private networkServicesList: NetworkService[]) {
    super();
    this.networkServiceSubject.next(networkServicesList);
  }

  connect(collectionViewer: CollectionViewer): Observable<NetworkService[]> {
    return this.networkServiceSubject.asObservable();
  }

  loadStatus() { 
    
  }

  disconnect(collectionViewer: CollectionViewer) {
    this.networkServiceSubject.complete();
    this.loadingSubject.complete();
  }
}
