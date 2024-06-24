import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as TailleEntrepriseActions from '../actions/tailleEntreprise.action';
import { TailleEntrepriseService } from '../../services/nomenclature.service';
import { Injectable } from '@angular/core';

@Injectable()
export class TailleEntrepriseEffects {

  loadTailleEntreprises$ = createEffect(() => this.actions$.pipe(
    ofType(TailleEntrepriseActions.loadTailleEntreprises),
    mergeMap(() => this.tailleEntrepriseService.getAllTailleEntreprises()
      .pipe(
        map(taillesEntreprises => {
          // Enregistrer les tailles d'entreprise dans le sessionStorage
          sessionStorage.setItem('taillesEntreprises', JSON.stringify(taillesEntreprises));
          return TailleEntrepriseActions.loadTailleEntreprisesSuccess({ taillesEntreprises });
        }), 
        catchError(error => of(TailleEntrepriseActions.loadTailleEntreprisesFailure({ error })))
      ))
    )
  );

  constructor(
    private actions$: Actions,
    private tailleEntrepriseService: TailleEntrepriseService
  ) {}
}
