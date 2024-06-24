import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Civilite } from '../models/civilite.model';
import { Category } from '../models/category.model';
import { Country } from '../models/country.model';
import { Gicscode } from '../models/gicscode.model';
import { LegalForm } from '../models/legalform.model';
import { Location } from '../models/location.model';
import { Missingsiren } from '../models/missingsiren.model';
import { Naf } from '../models/naf.model';
import { Roletype } from '../models/roletype.model';
import { Typeadress } from '../models/typeadress.model';
import { tap } from 'rxjs/operators';
import { TailleEntreprise } from '../models/tailleEntreprise.model';
import { apiUrl } from '../../../assets/environment';



const baseurl="http://localhost:8086" +
  "/glossaries"
@Injectable({
  providedIn: 'root'
})

export class CiviliteService {
  private apiUrl = baseurl+'/name-prefixes';

  constructor(private http: HttpClient) {}

  getCivilites(): Observable<Civilite[]> {
    return this.http.get<Civilite[]>(this.apiUrl);
  }
}


@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private apiUrl =baseurl+'/legal-categories';
  constructor (private http:HttpClient){}

  getCategories():Observable<Category[]>{
    return this.http.get<Category[]>(this.apiUrl);
  }

}

@Injectable({
  providedIn: 'root',
})
export class CountryService {
  private apiUrl = baseurl + '/countries';

  constructor(private http: HttpClient) { }

  getCountries(): Observable<Country[]> {
    return this.http.get<Country[]>(this.apiUrl).pipe(
      tap(data => {
        console.log('Données des pays récupérées :', data);
      })
    );
  }
}


@Injectable({
  providedIn: 'root',
})
export class gicscodeService {
  private apiurl=baseurl+'/gics-codes';
  constructor(private http:HttpClient){}
  getGicscode(): Observable <Gicscode[]>{
    return this.http.get<Gicscode[]>(this.apiurl);
  }
}


@Injectable({
  providedIn: 'root',
})
export class legalFormService {
  private apiurl=baseurl+'/legal-categories';
  constructor(private http:HttpClient){}
  getlegalForms(): Observable <LegalForm[]>{
    return this.http.get<LegalForm[]>(this.apiurl);
  }
}



@Injectable({
  providedIn: 'root',
})
export class locationsService {
  private apiurl=baseurl+'/france-locations';
  constructor(private http:HttpClient){}
  getfrancelocations(): Observable <Location[]>{
    return this.http.get<Location[]>(this.apiurl)
    .pipe(
      tap(data => {
        console.log('Données des villes récupérées :', data);
      })
    );
  }
}



@Injectable({
  providedIn: 'root',
})
export class missingsirenService {
  private apiurl=baseurl+'/missing-siren-siret-justifications';
  constructor(private http:HttpClient){}
  getmissingSiren(): Observable <Missingsiren[]>{
    return this.http.get<Missingsiren[]>(this.apiurl);
  }
}


@Injectable({
  providedIn: 'root',
})
export class nafService {
  private apiurl=baseurl+'/naf-codes';
  constructor(private http:HttpClient){}
  getnafcodes(): Observable <Naf[]>{
    return this.http.get<Naf[]>(this.apiurl);
  }
}



@Injectable({
  providedIn: 'root',
})
export class roleService {
  private apiurl=baseurl+'/person-role-types';
  constructor(private http:HttpClient){}
  getrole(): Observable <Roletype[]>{
    return this.http.get<Roletype[]>(this.apiurl);
  }
}



@Injectable({
  providedIn: 'root',
})
export class typeadressService {
  private apiurl=baseurl+'/physical-contact-point-types';
  constructor(private http:HttpClient){}
  gettypeadress(): Observable <Typeadress[]>{
    return this.http.get<Typeadress[]>(this.apiurl);
  }
}


@Injectable({
  providedIn: 'root',
})
export class TailleEntrepriseService {
  private apiUrl = 'http://localhost:8086/glossaries/european-organisation-party-sizes';

  constructor(private http: HttpClient) {}

  getAllTailleEntreprises(): Observable<TailleEntreprise[]> {
    return this.http.get<TailleEntreprise[]>(this.apiUrl);
  }
}

