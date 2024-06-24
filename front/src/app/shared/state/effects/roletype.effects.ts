import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as RoletypeActions from '../actions/roletype.actions';
import { roleService } from '../../services/nomenclature.service';
import { Injectable } from '@angular/core';

@Injectable()
export class RoletypeEffects {
  loadRoletypes$ = createEffect(() =>
    this.actions$.pipe(
      ofType(RoletypeActions.loadRoletypes),
      mergeMap(() =>
        this.roletypeService.getrole().pipe(
          map(roletypes => {
            // Enregistrer les types de rôle dans le sessionStorage
            sessionStorage.setItem('roletypes', JSON.stringify(roletypes));
            return RoletypeActions.loadRoletypesSuccess({ roletypes });
          }),
          catchError(error => of(RoletypeActions.loadRoletypesFailure({ error })))
        )
      )
    )
  );

  constructor(private actions$: Actions, private roletypeService: roleService) {}
}
