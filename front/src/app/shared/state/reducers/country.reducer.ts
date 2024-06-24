
import { createReducer, on, createFeatureSelector, createSelector } from '@ngrx/store';
import * as CountryActions from '../actions/country.action';
import { Country } from '../../models/country.model';

export interface CountryState {
  countries: Country[];
  loading: boolean;
  error: any;
}

export const initialState: CountryState = {
  countries: [],
  loading: false,
  error: null,
};

export const countryReducer = createReducer(
  initialState,
  on(CountryActions.loadCountries, state => ({ ...state, loading: true, error: null })),
  on(CountryActions.loadCountriesSuccess, (state, { countries }) => ({ ...state, countries, loading: false })),
  on(CountryActions.loadCountriesFailure, (state, { error }) => ({ ...state, loading: false, error }))
);

export const selectCountryState = createFeatureSelector<CountryState>('country');
export const selectCountries = createSelector(selectCountryState, (state) => (state ? state.countries : []));
export const selectCountryLoading = createSelector(selectCountryState, (state) => (state ? state.loading : false));
export const selectCountryError = createSelector(selectCountryState, (state) => (state ? state.error : null));
