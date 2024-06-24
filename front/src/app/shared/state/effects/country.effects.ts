import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as CountryActions from '../actions/country.action';
import { CountryService } from '../../services/nomenclature.service';
import { Injectable } from '@angular/core';

@Injectable()
export class CountryEffects {
  loadCountries$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CountryActions.loadCountries),
      mergeMap(() =>
        this.countryService.getCountries().pipe(
          map(countries => {
            sessionStorage.setItem('countries', JSON.stringify(countries));
            return CountryActions.loadCountriesSuccess({ countries });
          }),
          catchError(error => of(CountryActions.loadCountriesFailure({ error })))
        )
      )
    )
  );

  constructor(private actions$: Actions, private countryService: CountryService) {}
}
