
import { createAction, props } from '@ngrx/store';
import { Naf } from '../../models/naf.model';

export const loadNafs = createAction('[Naf] Load Nafs');
export const loadNafsSuccess = createAction('[Naf] Load Nafs Success', props<{ nafs: Naf[] }>());
export const loadNafsFailure = createAction('[Naf] Load Nafs Failure', props<{ error: any }>());
