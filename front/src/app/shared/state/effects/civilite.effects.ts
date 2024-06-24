import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap, tap } from 'rxjs/operators';
import * as CiviliteActions from '../actions/civilitie.action';
import { CiviliteService } from '../../services/nomenclature.service';
import { Injectable } from '@angular/core';

@Injectable()
export class CiviliteEffects {
  loadCivilites$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CiviliteActions.loadCivilites),
      mergeMap(() =>
        this.civiliteService.getCivilites().pipe(
          map(civilites => {
            sessionStorage.setItem('civilites', JSON.stringify(civilites));
            return CiviliteActions.loadCivilitesSuccess({ civilites });
          }),
          catchError(error => of(CiviliteActions.loadCivilitesFailure({ error })))
        )
      )
    )
  );

  constructor(private actions$: Actions, private civiliteService: CiviliteService) {}
}
