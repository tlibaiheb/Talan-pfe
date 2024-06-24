import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as GicscodeActions from '../actions/gicscode.actions';
import { gicscodeService } from '../../services/nomenclature.service';
import { Injectable } from '@angular/core';

@Injectable()
export class GicscodeEffects {
  loadGicscodes$ = createEffect(() =>
    this.actions$.pipe(
      ofType(GicscodeActions.loadGicscodes),
      mergeMap(() =>
        this.gicscodeService.getGicscode().pipe(
          map(gicscodes => {
            // Enregistrer les codes GICS dans le sessionStorage
            sessionStorage.setItem('gicscodes', JSON.stringify(gicscodes));
            return GicscodeActions.loadGicscodesSuccess({ gicscodes });
          }),
          catchError(error => of(GicscodeActions.loadGicscodesFailure({ error })))
        )
      )
    )
  );

  constructor(private actions$: Actions, private gicscodeService: gicscodeService) {}
}
