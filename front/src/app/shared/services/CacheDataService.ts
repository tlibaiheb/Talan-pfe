import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CacheDataService {
  constructor(private store: Store) {}

  // Méthode pour vérifier si les données sont présentes dans le cache et les charger si nécessaire
  checkAndLoadData(data$: Observable<any>, loadAction: any) {
    data$.subscribe((data) => {
      if (!data || data.length === 0) {
        this.store.dispatch(loadAction());
      }
    });
  }
}
