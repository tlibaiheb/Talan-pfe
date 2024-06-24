import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ButtonModule } from 'primeng/button';
import { HeaderModule } from './core/header/header.module';
import { FooterModule } from './core/footer/footer.module';

import { ActionReducer, StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { civiliteReducer } from './shared/state/reducers/civilite.reducer';
import { CiviliteEffects } from './shared/state/effects/civilite.effects';

import { HttpClientModule } from '@angular/common/http';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';



import { CategoryEffects } from './shared/state/effects/category.effects';
import { categoryReducer } from './shared/state/reducers/category.reducer';
import { countryReducer } from './shared/state/reducers/country.reducer';
import { gicscodeReducer } from './shared/state/reducers/gicscode.reducer';
import { legalFormReducer } from './shared/state/reducers/legal-form.reducer';
import { locationReducer } from './shared/state/reducers/location.reducer';
import { missingsirenReducer } from './shared/state/reducers/missingsiren.reducer';
import { nafReducer } from './shared/state/reducers/naf.reducer';
import { roletypeReducer } from './shared/state/reducers/roletype.reducer';
import { typeadressReducer } from './shared/state/reducers/typeadress.reducer';
import { GicscodeEffects } from './shared/state/effects/gicscode.effects';
import { LegalFormEffects } from './shared/state/effects/legal-form.effects';
import { LocationEffects } from './shared/state/effects/location.effects';
import { MissingsirenEffects } from './shared/state/effects/missingsiren.effects';
import { NafEffects } from './shared/state/effects/naf.effects';
import { RoletypeEffects } from './shared/state/effects/roletype.effects';
import { TypeadressEffects } from './shared/state/effects/typeadress.effects';
import { TailleEntrepriseEffects } from './shared/state/effects/tailleEntreprise.effects';
import { tailleEntrepriseReducer } from './shared/state/reducers/taille-entreprise.reducer';
import { CountryEffects } from './shared/state/effects/country.effects';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthModule } from './core/auth/auth.module';
import { AuthGuard } from './shared/services/auth.guard';




export function sessionStorageSyncReducer(reducer: ActionReducer<any, any>): ActionReducer<any, any> {
  return (state, action) => {
    const newState = reducer(state, action);
    sessionStorage.setItem('appState', JSON.stringify(newState));
    return newState;
  };
}



@NgModule({
  declarations: [
    AppComponent,

  ],
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    ButtonModule,
    AppRoutingModule,
    HeaderModule,
    AuthModule,


    FooterModule,
    StoreModule.forRoot({
      civilite: civiliteReducer ,
      category:categoryReducer,
      country: countryReducer ,
      gicscode:gicscodeReducer ,
      legalform: legalFormReducer ,
      location: locationReducer,
      missingsiren:missingsirenReducer ,
      naf: nafReducer,
      roletype:roletypeReducer ,
      tailleEntreprise: tailleEntrepriseReducer,
      typeadress: typeadressReducer}),
    StoreModule.forRoot({
      civilite: civiliteReducer,
      category: categoryReducer,
      country: countryReducer,
      gicscode: gicscodeReducer,
      legalform: legalFormReducer,
      location: locationReducer,
      missingsiren: missingsirenReducer,
      naf: nafReducer,
      roletype: roletypeReducer,
      typeadress: typeadressReducer,
      tailleEntreprise: tailleEntrepriseReducer }),
    EffectsModule.forRoot([
      CiviliteEffects,
      CategoryEffects,
      CountryEffects,
      GicscodeEffects,
      LegalFormEffects,
      LocationEffects,
      MissingsirenEffects,
      NafEffects,
      RoletypeEffects,
      TypeadressEffects,
      TailleEntrepriseEffects,
      CountryEffects
    ]),
      StoreDevtoolsModule.instrument(),

  ],
  providers: [AuthGuard],
    bootstrap: [AppComponent]
})
export class AppModule { }
