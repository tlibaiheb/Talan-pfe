
import { createAction, props } from '@ngrx/store';
import { Missingsiren } from '../../models/missingsiren.model';

export const loadMissingsirens = createAction('[Missingsiren] Load Missingsirens');
export const loadMissingsirensSuccess = createAction('[Missingsiren] Load Missingsirens Success', props<{ missingsirens: Missingsiren[] }>());
export const loadMissingsirensFailure = createAction('[Missingsiren] Load Missingsirens Failure', props<{ error: any }>());
