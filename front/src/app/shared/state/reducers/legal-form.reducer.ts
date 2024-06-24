
import { createReducer, on, createFeatureSelector, createSelector } from '@ngrx/store';
import * as LegalFormActions from '../actions/legal-form.actions';
import { LegalForm } from '../../models/legalform.model';

export interface LegalFormState {
  legalForms: LegalForm[];
  loading: boolean;
  error: any;
}

export const initialState: LegalFormState = {
  legalForms: [],
  loading: false,
  error: null,
};

export const legalFormReducer = createReducer(
  initialState,
  on(LegalFormActions.loadLegalForms, state => ({ ...state, loading: true, error: null })),
  on(LegalFormActions.loadLegalFormsSuccess, (state, { legalForms }) => ({ ...state, legalForms, loading: false })),
  on(LegalFormActions.loadLegalFormsFailure, (state, { error }) => ({ ...state, loading: false, error }))
);

export const selectLegalFormState = createFeatureSelector<LegalFormState>('legalForm');
export const selectLegalForms = createSelector(selectLegalFormState, (state) => (state ? state.legalForms : []));
export const selectLegalFormLoading = createSelector(selectLegalFormState, (state) => (state ? state.loading : false));
export const selectLegalFormError = createSelector(selectLegalFormState, (state) => (state ? state.error : null));
