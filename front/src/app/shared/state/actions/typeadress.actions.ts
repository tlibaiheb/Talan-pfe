
import { createAction, props } from '@ngrx/store';
import { Typeadress } from '../../models/typeadress.model';

export const loadTypeadresses = createAction('[Typeadress] Load Typeadresses');
export const loadTypeadressesSuccess = createAction('[Typeadress] Load Typeadresses Success', props<{ typeadresses: Typeadress[] }>());
export const loadTypeadressesFailure = createAction('[Typeadress] Load Typeadresses Failure', props<{ error: any }>());
