import { createAction, props } from '@ngrx/store';
import { TailleEntreprise } from '../../models/tailleEntreprise.model';

// Action pour charger les tailles d'entreprises
export const loadTailleEntreprises = createAction('[TailleEntreprise] Load TailleEntreprises');

// Action pour indiquer que le chargement des tailles d'entreprises a réussi
export const loadTailleEntreprisesSuccess = createAction(
  '[TailleEntreprise] Load TailleEntreprises Success',
  props<{ taillesEntreprises: TailleEntreprise[] }>()
);

// Action pour indiquer que le chargement des tailles d'entreprises a échoué
export const loadTailleEntreprisesFailure = createAction(
  '[TailleEntreprise] Load TailleEntreprises Failure',
  props<{ error: any }>()
);
