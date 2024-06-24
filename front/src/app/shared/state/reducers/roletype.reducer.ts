
import { createReducer, on, createFeatureSelector, createSelector } from '@ngrx/store';
import * as RoletypeActions from '../actions/roletype.actions';
import { Roletype } from '../../models/roletype.model';

export interface RoletypeState {
  roletypes: Roletype[];
  loading: boolean;
  error: any;
}

export const initialState: RoletypeState = {
  roletypes: [],
  loading: false,
  error: null,
};

export const roletypeReducer = createReducer(
  initialState,
  on(RoletypeActions.loadRoletypes, state => ({ ...state, loading: true, error: null })),
  on(RoletypeActions.loadRoletypesSuccess, (state, { roletypes }) => ({ ...state, roletypes, loading: false })),
  on(RoletypeActions.loadRoletypesFailure, (state, { error }) => ({ ...state, loading: false, error }))
);

export const selectRoletypeState = createFeatureSelector<RoletypeState>('roletype');
export const selectRoletypes = createSelector(selectRoletypeState, (state) => (state ? state.roletypes : []));
export const selectRoletypeLoading = createSelector(selectRoletypeState, (state) => (state ? state.loading : false));
export const selectRoletypeError = createSelector(selectRoletypeState, (state) => (state ? state.error : null));
