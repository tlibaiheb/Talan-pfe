import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as TypeadressActions from '../actions/typeadress.actions';
import { typeadressService } from '../../services/nomenclature.service';
import { Injectable } from '@angular/core';

@Injectable()
export class TypeadressEffects {
  loadTypeadresses$ = createEffect(() =>
    this.actions$.pipe(
      ofType(TypeadressActions.loadTypeadresses),
      mergeMap(() =>
        this.typeadressService.gettypeadress().pipe(
          map(typeadresses => {
            // Enregistrer les types d'adresse dans le sessionStorage
            sessionStorage.setItem('typeadresses', JSON.stringify(typeadresses));
            return TypeadressActions.loadTypeadressesSuccess({ typeadresses });
          }),
          catchError(error => of(TypeadressActions.loadTypeadressesFailure({ error })))
        )
      )
    )
  );

  constructor(private actions$: Actions, private typeadressService: typeadressService) {}
}
