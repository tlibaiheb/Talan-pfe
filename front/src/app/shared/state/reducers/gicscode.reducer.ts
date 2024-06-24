
import { createReducer, on, createFeatureSelector, createSelector } from '@ngrx/store';
import * as GicscodeActions from '../actions/gicscode.actions';
import { Gicscode } from '../../models/gicscode.model';

export interface GicscodeState {
  gicscodes: Gicscode[];
  loading: boolean;
  error: any;
}

export const initialState: GicscodeState = {
  gicscodes: [],
  loading: false,
  error: null,
};

export const gicscodeReducer = createReducer(
  initialState,
  on(GicscodeActions.loadGicscodes, state => ({ ...state, loading: true, error: null })),
  on(GicscodeActions.loadGicscodesSuccess, (state, { gicscodes }) => ({ ...state, gicscodes, loading: false })),
  on(GicscodeActions.loadGicscodesFailure, (state, { error }) => ({ ...state, loading: false, error }))
);

export const selectGicscodeState = createFeatureSelector<GicscodeState>('gicscode');
export const selectGicscodes = createSelector(selectGicscodeState, (state) => (state ? state.gicscodes : []));
export const selectGicscodeLoading = createSelector(selectGicscodeState, (state) => (state ? state.loading : false));
export const selectGicscodeError = createSelector(selectGicscodeState, (state) => (state ? state.error : null));