import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { apiUrl} from '../../../assets/environment';

@Injectable({
  providedIn: 'root'
})
export class OrganisationPartyService {
  private url = 'http://localhost:8086/api/v1/search' ;
  private apiUrl = 'http://localhost:8086/api/v1/organisation-parties';

  constructor(private http: HttpClient) { }

  createOrganisationParty(request: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, request).pipe(
      catchError(error => throwError(error))
    );
  }

  createAddress(organisationPId: number, addressForm: any): Observable<any> {
    const url = `${this.apiUrl}/${organisationPId}/physical-contact-points`;
    return this.http.post<any>(url, addressForm).pipe(
      catchError(error => throwError(error))
    );
  }


    getOrganisationPartyById(id: number): Observable<any> {
      const url = `${this.apiUrl}/getbyid/${id}`;
      return this.http.get<any>(url).pipe(
        catchError(error => throwError(error))
      );
    }

    getAdressById(id:number):Observable<any>{
      const url=`${this.apiUrl}/physical-contact-points/${id}`;
      return this.http.get<any>(url).pipe(
        catchError(error => throwError(error))
      );    }


      updateOrganisationParty(id: number, request: any): Observable<any> {
        const url = `${this.apiUrl}/update/${id}`;
        return this.http.put<any>(url, request).pipe(
          catchError(error => throwError(error))
        );
      }

      getAllOrganisationParties(): Observable<any[]> {
        return this.http.get<any[]>(this.apiUrl).pipe(
          catchError(error => throwError(error))
        );
      }


      filtrerListe(selectedOption: string, searchValue: string): Observable<any[]> {
        const url = `${this.apiUrl}/filtrer?selectedOption=${selectedOption}&searchValue=${searchValue}`;
        return this.http.get<any[]>(url).pipe(
          catchError(error => throwError(error))
        );
      }


      checkSirenExistence(siren: string): Observable<boolean> {
        const url = `${this.apiUrl}/existsBySiren/${siren}`;
        return this.http.get<boolean>(url);
      }



      getOrganisationPartyBySiren(siren: string): Observable<any> {
        const url = `${this.apiUrl}/getbysiren/${siren}`;
        return this.http.get<any>(url).pipe(
          catchError(error => throwError(error))
        );
      }

      getEtablissementData(siren: string): Observable<any> {
        const url = `http://localhost:8086/api/v1/etablissements/${siren}`;
        return this.http.get<any>(url).pipe(
          catchError(error => throwError(error))
        );
      }



  searchByNomComplet(nomComplet: string): Observable<string> {
    // Ajoutez le nomComplet comme paramètre à la requête GET
    const params = new HttpParams().set('nomComplet', nomComplet);

    // Effectuez la requête GET vers votre endpoint search avec les paramètres appropriés
    return this.http.get<string>(this.url, { params }).pipe(
      catchError(error => throwError(error))
    );
  }




}
