
import { createReducer, on, createFeatureSelector, createSelector } from '@ngrx/store';
import * as NafActions from '../actions/naf.actions';
import { Naf } from '../../models/naf.model';

export interface NafState {
  nafs: Naf[];
  loading: boolean;
  error: any;
}

export const initialState: NafState = {
  nafs: [],
  loading: false,
  error: null,
};

export const nafReducer = createReducer(
  initialState,
  on(NafActions.loadNafs, state => ({ ...state, loading: true, error: null })),
  on(NafActions.loadNafsSuccess, (state, { nafs }) => ({ ...state, nafs, loading: false })),
  on(NafActions.loadNafsFailure, (state, { error }) => ({ ...state, loading: false, error }))
);

export const selectNafState = createFeatureSelector<NafState>('naf');
export const selectNafs = createSelector(selectNafState, (state) => (state ? state.nafs : []));
export const selectNafLoading = createSelector(selectNafState, (state) => (state ? state.loading : false));
export const selectNafError = createSelector(selectNafState, (state) => (state ? state.error : null));
