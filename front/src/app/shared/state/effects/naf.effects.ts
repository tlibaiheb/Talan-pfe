import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as NafActions from '../actions/naf.actions';
import { nafService } from '../../services/nomenclature.service';
import { Injectable } from '@angular/core';

@Injectable()
export class NafEffects {
  loadNafs$ = createEffect(() =>
    this.actions$.pipe(
      ofType(NafActions.loadNafs),
      mergeMap(() =>
        this.nafService.getnafcodes().pipe(
          map(nafs => {
            // Enregistrer les codes NAF dans le sessionStorage
            sessionStorage.setItem('nafs', JSON.stringify(nafs));
            return NafActions.loadNafsSuccess({ nafs });
          }),
          catchError(error => of(NafActions.loadNafsFailure({ error })))
        )
      )
    )
  );

  constructor(private actions$: Actions, private nafService: nafService) {}
}
