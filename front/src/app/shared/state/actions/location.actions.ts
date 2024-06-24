
import { createAction, props } from '@ngrx/store';
import { Location } from '../../models/location.model';


export const loadLocations = createAction('[Location] Load Locations');
export const loadLocationsSuccess = createAction('[Location] Load Locations Success', props<{ locations: Location[] }>());
export const loadLocationsFailure = createAction('[Location] Load Locations Failure', props<{ error: any }>());
