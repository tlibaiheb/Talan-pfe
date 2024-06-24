import { createReducer, on, createFeatureSelector, createSelector } from '@ngrx/store';
import * as LocationActions from '../actions/location.actions';
import { Location } from '../../models/location.model';

export interface LocationState {
  locations: Location[];
  loading: boolean;
  error: any;
}

export const initialState: LocationState = {
  locations: [],
  loading: false,
  error: null,
};

export const locationReducer = createReducer(
  initialState,
  on(LocationActions.loadLocations, state => ({ ...state, loading: true, error: null })),
  on(LocationActions.loadLocationsSuccess, (state, { locations }) => ({ ...state, locations, loading: false })),
  on(LocationActions.loadLocationsFailure, (state, { error }) => ({ ...state, loading: false, error }))
);

export const selectLocationState = createFeatureSelector<LocationState>('location');
export const selectLocations = createSelector(selectLocationState, (state) => (state ? state.locations : []));
export const selectLocationLoading = createSelector(selectLocationState, (state) => (state ? state.loading : false));
export const selectLocationError = createSelector(selectLocationState, (state) => (state ? state.error : null));

