import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
 import { tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class UserService {
    private baseUrl = 'http://localhost:3000/auth/register';

    constructor(private http: HttpClient) { }

    register(user: any): Observable<any> {
      return this.http.post<any>(this.baseUrl, user);
    }
  }



  @Injectable({
    providedIn: 'root'
  })
  export class AuthService {
    private baseUrl = 'http://localhost:3000/auth';

    private tokenKey = 'auth_token';

    constructor(private http: HttpClient) { }

    login(email: string, password: string): Observable<any> {
      return this.http.post<any>(`${this.baseUrl}/login`, { email, password }).pipe(
        tap(response => {

          // localStorage.setItem(this.tokenKey, response.token);

        })
      );
    }

    logout(): Observable<any> {
      return this.http.get<any>(`${this.baseUrl}/logout`, {}).pipe(
        tap(() => {

          // localStorage.removeItem(this.tokenKey);
          localStorage.removeItem("token");

        })
      );
    }










  }
