
import { createReducer, on, createFeatureSelector, createSelector } from '@ngrx/store';
import * as MissingsirenActions from '../actions/missingsiren.actions';
import { Missingsiren } from '../../models/missingsiren.model';

export interface MissingsirenState {
  missingsirens: Missingsiren[];
  loading: boolean;
  error: any;
}

export const initialState: MissingsirenState = {
  missingsirens: [],
  loading: false,
  error: null,
};

export const missingsirenReducer = createReducer(
  initialState,
  on(MissingsirenActions.loadMissingsirens, state => ({ ...state, loading: true, error: null })),
  on(MissingsirenActions.loadMissingsirensSuccess, (state, { missingsirens }) => ({ ...state, missingsirens, loading: false })),
  on(MissingsirenActions.loadMissingsirensFailure, (state, { error }) => ({ ...state, loading: false, error }))
);

export const selectMissingsirenState = createFeatureSelector<MissingsirenState>('missingsiren');
export const selectMissingsirens = createSelector(selectMissingsirenState, (state) => (state ? state.missingsirens : []));
export const selectMissingsirenLoading = createSelector(selectMissingsirenState, (state) => (state ? state.loading : false));
export const selectMissingsirenError = createSelector(selectMissingsirenState, (state) => (state ? state.error : null));
