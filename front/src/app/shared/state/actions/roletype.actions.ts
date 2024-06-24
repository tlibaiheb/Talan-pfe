
import { createAction, props } from '@ngrx/store';
import { Roletype } from '../../models/roletype.model';

export const loadRoletypes = createAction('[Roletype] Load Roletypes');
export const loadRoletypesSuccess = createAction('[Roletype] Load Roletypes Success', props<{ roletypes: Roletype[] }>());
export const loadRoletypesFailure = createAction('[Roletype] Load Roletypes Failure', props<{ error: any }>());
