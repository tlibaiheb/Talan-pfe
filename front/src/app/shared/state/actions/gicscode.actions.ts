
import { createAction, props } from '@ngrx/store';
import { Gicscode } from '../../models/gicscode.model';

export const loadGicscodes = createAction('[Gicscode] Load Gicscodes');
export const loadGicscodesSuccess = createAction('[Gicscode] Load Gicscodes Success', props<{ gicscodes: Gicscode[] }>());
export const loadGicscodesFailure = createAction('[Gicscode] Load Gicscodes Failure', props<{ error: any }>());
