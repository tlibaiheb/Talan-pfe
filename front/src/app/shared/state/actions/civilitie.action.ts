
import { createAction, props } from '@ngrx/store';
import { Civilite } from '../../models/civilite.model';



// Création d'une action pour le chargement des civilites
export const loadCivilites = createAction('[Civilite] Load Civilites');

// Création d'une action pour le succès du chargement des civilites
// On utilise props pour spécifier les données (civilites) à associer à l'action
export const loadCivilitesSuccess = createAction('[Civilite] Load Civilites Success', props<{ civilites: Civilite[] }>());

// Création d'une action en cas d'échec du chargement des civilites
// On utilise props pour spécifier les données (erreur) à associer à l'action
export const loadCivilitesFailure = createAction('[Civilite] Load Civilites Failure', props<{ error: any }>());
