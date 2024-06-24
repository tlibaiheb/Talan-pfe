import { createReducer, on, createFeatureSelector, createSelector } from '@ngrx/store';
import * as TailleEntrepriseActions from '../actions/tailleEntreprise.action';
import { TailleEntreprise } from '../../models/tailleEntreprise.model';

// Interface définissant la structure de l'état de la taille de l'entreprise
export interface TailleEntrepriseState {
  taillesEntreprises: TailleEntreprise[];
  loading: boolean;  // Indicateur de chargement
  error: any;
}

// État initial de la taille de l'entreprise
export const initialState: TailleEntrepriseState = {
  taillesEntreprises: [],
  loading: false,
  error: null,
};

export const tailleEntrepriseReducer = createReducer(
  initialState,
  on(TailleEntrepriseActions.loadTailleEntreprises, state => ({ ...state, loading: true, error: null })),
  on(TailleEntrepriseActions.loadTailleEntreprisesSuccess, (state, { taillesEntreprises }) => ({ ...state, taillesEntreprises, loading: false })),
  on(TailleEntrepriseActions.loadTailleEntreprisesFailure, (state, { error }) => ({ ...state, loading: false, error }))
);

// Sélecteurs pour accéder à des parties spécifiques de l'état global
export const selectTailleEntrepriseState = createFeatureSelector<TailleEntrepriseState>('tailleEntreprise');

// Sélecteur pour accéder au tableau des tailles d'entreprises
export const selectTailleEntreprises = createSelector(
  selectTailleEntrepriseState,
  (state) =>  (state ? state.taillesEntreprises:[])
);

// Sélecteur pour accéder à l'indicateur de chargement
export const selectTailleEntrepriseLoading = createSelector(
  selectTailleEntrepriseState,
  (state) => state.loading
);

// Sélecteur pour accéder à l'objet représentant une erreur
export const selectTailleEntrepriseError = createSelector(
  selectTailleEntrepriseState,
  (state) => state.error
);
