import { createReducer, on, createFeatureSelector, createSelector } from '@ngrx/store';
import * as CiviliteActions from '../actions/civilitie.action';
import { Civilite } from '../../models/civilite.model';

// Interface définissant la structure de l'état de la nomenclature
export interface CiviliteState {
  civilites: Civilite[];
  loading: boolean;  // Indicateur de chargement
  error: any;
}

// État initial de la nomenclature
export const initialState: CiviliteState = {
  civilites: [],
  loading: false,
  error: null,
};



export const civiliteReducer = createReducer(
  initialState,
  // Gestion de l'action 'loadCivilites' (début du chargement des civilites)
  on(CiviliteActions.loadCivilites, state => ({ ...state, loading: true, error: null })),
  // Gestion de l'action 'loadCivilitesSuccess' (chargement réussi des civilites)
  on(CiviliteActions.loadCivilitesSuccess, (state, { civilites }) => ({ ...state, civilites, loading: false })),
  // Gestion de l'action 'loadCivilitesFailure' (échec du chargement des civilites)
  on(CiviliteActions.loadCivilitesFailure, (state, { error }) => ({ ...state, loading: false, error }))
);



// Sélecteurs (selectors) pour accéder à des parties spécifiques de l'état global
export const selectCiviliteState = createFeatureSelector<CiviliteState>('civilite');



// Sélecteur pour accéder au tableau des civilites
export const selectCivilites = createSelector(
  selectCiviliteState,
  (state) => state.civilites
);



// Sélecteur pour accéder à l'indicateur de chargement
export const selectLoading = createSelector(
  selectCiviliteState,
  (state) => state.loading
);



// Sélecteur pour accéder à l'objet représentant une erreur
export const selectError = createSelector(
  selectCiviliteState,
  (state) => state.error
);
