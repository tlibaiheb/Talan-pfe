import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as MissingsirenActions from '../actions/missingsiren.actions';
import { missingsirenService } from '../../services/nomenclature.service';
import { Injectable } from '@angular/core';

@Injectable()
export class MissingsirenEffects {
  loadMissingsirens$ = createEffect(() =>
    this.actions$.pipe(
      ofType(MissingsirenActions.loadMissingsirens),
      mergeMap(() =>
        this.missingsirenService.getmissingSiren().pipe(
          map(missingsirens => {
            // Enregistrer les données récupérées dans le sessionStorage
            sessionStorage.setItem('missingsirens', JSON.stringify(missingsirens));
            return MissingsirenActions.loadMissingsirensSuccess({ missingsirens });
          }),
          catchError(error => of(MissingsirenActions.loadMissingsirensFailure({ error })))
        )
      )
    )
  );

  constructor(private actions$: Actions, private missingsirenService: missingsirenService) {}
}
