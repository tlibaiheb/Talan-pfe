
import { createAction, props } from '@ngrx/store';
import { LegalForm } from '../../models/legalform.model';

export const loadLegalForms = createAction('[LegalForm] Load LegalForms');
export const loadLegalFormsSuccess = createAction('[LegalForm] Load LegalForms Success', props<{ legalForms: LegalForm[] }>());
export const loadLegalFormsFailure = createAction('[LegalForm] Load LegalForms Failure', props<{ error: any }>());
