
import { createReducer, on, createFeatureSelector, createSelector } from '@ngrx/store';
import * as TypeadressActions from '../actions/typeadress.actions';
import { Typeadress } from '../../models/typeadress.model';

export interface TypeadressState {
  typeadresses: Typeadress[];
  loading: boolean;
  error: any;
}

export const initialState: TypeadressState = {
  typeadresses: [],
  loading: false,
  error: null,
};

export const typeadressReducer = createReducer(
  initialState,
  on(TypeadressActions.loadTypeadresses, state => ({ ...state, loading: true, error: null })),
  on(TypeadressActions.loadTypeadressesSuccess, (state, { typeadresses }) => ({ ...state, typeadresses, loading: false })),
  on(TypeadressActions.loadTypeadressesFailure, (state, { error }) => ({ ...state, loading: false, error }))
);

export const selectTypeadressState = createFeatureSelector<TypeadressState>('typeadress');
export const selectTypeadresses = createSelector(selectTypeadressState, (state) => (state ? state.typeadresses : []));
export const selectTypeadressLoading = createSelector(selectTypeadressState, (state) => (state ? state.loading : false));
export const selectTypeadressError = createSelector(selectTypeadressState, (state) => (state ? state.error : null));
