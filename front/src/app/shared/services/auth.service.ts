// Angular Service pour gérer les appels API
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { apiUrl } from '../../../assets/environment';

@Injectable({
  providedIn: 'root'
})
// export class AuthService {

//   apiUrl = environment.apiUrl;

//   constructor(private http: HttpClient) { }

//   registerUser(userData: any): Observable<any> {
//     return this.http.post(this.apiUrl+'/api/v1/registration', userData);
//   }

//   verifyEmail(token: string): Observable<any> {
//     return this.http.get(this.apiUrl+`/api/v1/registration/confirm?token=${token}`);
//   }

//   loginUser(credentials: any): Observable<{ token: string }> {
//     return this.http.post<{ token: string }>(this.apiUrl + '/api/v1/login', credentials).pipe(
//       tap(response => {
//         // localStorage.setItem('token', response.token);
//       })
//     );
//   }


//   logout(): Observable<any> {
//     return this.http.get(this.apiUrl+'/api/v1/logout').pipe(
//       tap(() => {
//         localStorage.removeItem('token');
//       })
//     );
//   }


//   forgotPassword(email: string): Observable<any> {
//     return this.http.post(`${this.apiUrl+"/api/v1"}/forgot-password`, { email });
//   }


//   getUserFromToken(token: string): Observable<any> {
//     return this.http.get(this.apiUrl+'/api/v1/user', {
//       headers: {
//         Authorization: `Bearer ${token}`
//       }
//     });
//   }

// }

/*export class AuthService {


  constructor(private http: HttpClient) { }

  registerUser(userData: any): Observable<any> {
    return this.http.post(`http://98.66.200.112:8086/api/v1/registration`, userData);
  }

  verifyEmail(token: string): Observable<any> {
    return this.http.get(`http://98.66.200.112:8086/api/v1/registration/confirm?token=${token}`);
  }

  loginUser(credentials: any): Observable<{ token: string }> {
    return this.http.post<{ token: string }>( `http://98.66.200.112:8086/api/v1/login`, credentials);
  }

  logout(): Observable<any> {
    return this.http.get( `http://98.66.200.112:8086/api/v1/logout`);
  }

  forgotPassword(email: string): Observable<any> {
    return this.http.post( `http://98.66.200.112:8086/api/v1/forgot-password`, { email });
  }

  getUserFromToken(token: string): Observable<any> {
    return this.http.get( `http://98.66.200.112:8086/api/v1/user`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  }

}*/

export class AuthService {

  constructor(private http: HttpClient) { }

  registerUser(userData: any): Observable<any> {
    return this.http.post('http://localhost:8086/api/v1/registration', userData);
  }

  verifyEmail(token: string): Observable<any> {
    return this.http.get(`http://localhost:8086/api/v1/registration/confirm?token=${token}`);
  }

  loginUser(credentials: any): Observable<{ token: string }> {
    return this.http.post<{ token: string }>('http://localhost:8086/api/v1/login', credentials).pipe(
      tap(response => {
        // localStorage.setItem('token', response.token);
      })
    );
  }


  logout(): Observable<any> {
    return this.http.get('http://localhost:8086/api/v1/logout').pipe(
      tap(() => {
        localStorage.removeItem('token');
      })
    );
  }


  forgotPassword(email: string): Observable<any> {
    return this.http.post(`${"http://localhost:8086/api/v1"}/forgot-password`, { email });
  }


  getUserFromToken(token: string): Observable<any> {
    return this.http.get('http://localhost:8086/api/v1/user', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  }

}
