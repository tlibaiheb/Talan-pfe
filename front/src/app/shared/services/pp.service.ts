import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { apiUrl } from '../../../assets/environment';
 @Injectable({
  providedIn: 'root'
})
export class PersonPartyService {

  private apiUrl = 'http://localhost:8086/api/v1/person-parties';

  constructor(private http: HttpClient) { }

  createPersonParties(request: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, request).pipe(
      catchError(error => throwError(error))
    );
  }

  createPhysicalContactPoint(personPId: number, physicalContactPoint: any): Observable<any> {
    const url = `${this.apiUrl}/${personPId}/physical-contact-points`;
    return this.http.post<any>(url, physicalContactPoint).pipe(
      catchError(error => throwError(error))
    );
  }

  getAdressById(id:number):Observable<any>{
    const url=`${this.apiUrl}/physical-contact-points/${id}`;
    return this.http.get<any>(url).pipe(
      catchError(error => throwError(error))
    );    }

    getPersonPartyById(id: number): Observable<any> {
      const url = `${this.apiUrl}/getbyid/${id}`;
      return this.http.get<any>(url).pipe(
        catchError(error => throwError(error))
      );
    }


//update
// Méthode pour mettre à jour une partie de personne
updatePersonParty(id: number, updatedData: any): Observable<any> {
  const url = `${this.apiUrl}/${id}`;
  return this.http.put<any>(url, updatedData).pipe(
    catchError(error => throwError(error))
  );
}



getAllPersonParties(): Observable<any[]> {
  return this.http.get<any[]>(this.apiUrl).pipe(
    catchError(error => throwError(error))
  );
}

getPersonPartiesByCpNumber(cpNumber: string): Observable<any[]> {
  const url = `${this.apiUrl}/by-cp-number/${cpNumber}`;
  return this.http.get<any[]>(url).pipe(
    catchError(error => throwError(error))
  );
}

getPersonPartiesByIdTiers(idTiers: string): Observable<any[]> {
  const url = `${this.apiUrl}/by-idTiers/${idTiers}`;
  return this.http.get<any[]>(url).pipe(
    catchError(error => throwError(error))
  );
}

 // Méthode pour récupérer les personnes physiques par le nom de naissance
 getPersonPartiesByBirthName(birthName: string): Observable<any[]> {
  const url = `${this.apiUrl}/by-birth-name/${birthName}`;
  return this.http.get<any[]>(url).pipe(
    catchError(error => throwError(error))
  );
}

getPersonPartiesByMainFirstName(mainFirstName: string): Observable<any[]> {
  const url = `${this.apiUrl}/by-main-first-name/${mainFirstName}`;
  return this.http.get<any[]>(url).pipe(
    catchError(error => throwError(error))
  );
}


searchPersonByCP(cpNumber: string): Observable<any> {
  const url = `${this.apiUrl}/search-by-cp/${cpNumber}`;
  return this.http.get<any>(url).pipe(
    catchError(error => throwError(error))
  );
}
}


