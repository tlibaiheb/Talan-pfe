// 

import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as LocationActions from '../actions/location.actions';
import { locationsService } from '../../services/nomenclature.service';
import { Injectable } from '@angular/core';

@Injectable()
export class LocationEffects {
  loadLocations$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LocationActions.loadLocations),
      mergeMap(() =>
        this.locationService.getfrancelocations().pipe(
          map(locations => LocationActions.loadLocationsSuccess({ locations })),
          catchError(error => of(LocationActions.loadLocationsFailure({ error })))
        )
      )
    )
  );

  constructor(private actions$: Actions, private locationService: locationsService) {}
}
