import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as LegalFormActions from '../actions/legal-form.actions';
import { legalFormService } from '../../services/nomenclature.service';
import { Injectable } from '@angular/core';

@Injectable()
export class LegalFormEffects {
  loadLegalForms$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LegalFormActions.loadLegalForms),
      mergeMap(() =>
        this.legalFormService.getlegalForms().pipe(
          map(legalForms => {
            // Enregistrer les formes légales dans le sessionStorage
            sessionStorage.setItem('legalForms', JSON.stringify(legalForms));
            return LegalFormActions.loadLegalFormsSuccess({ legalForms });
          }),
          catchError(error => of(LegalFormActions.loadLegalFormsFailure({ error })))
        )
      )
    )
  );

  constructor(private actions$: Actions, private legalFormService: legalFormService) {}
}
