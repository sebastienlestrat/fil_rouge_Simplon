import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EMPTY, Observable, catchError, map } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private readonly BASE_URL = environment.url_api;

  constructor(protected http: HttpClient) {}

  /**
   * Connexion a l'API
   * @param email : email
   * @param password : mot de passe
   * @returns
   */
  login(email: string, password: string): Observable<string> {
    return this.http
      .post<{ accessToken: string }>(`${this.BASE_URL}/api/auth/signin`, {
        email,
        password,
      })
      .pipe(
        map((tokenResponse) => {
          this.setSession('accessToken', tokenResponse.accessToken);
          return tokenResponse.accessToken;
        }),
        catchError((err, caught) => {
          console.error('login-Error :', err, caught);
          return EMPTY;
        })
      );
  }

  signUp(
    username: string,
    email: string,
    password: string
  ): Observable<string> {
    return this.http
      .post<{ accessToken: string }>(`${this.BASE_URL}/api/auth/signup`, {
        username,
        email,
        password,
      })
      .pipe(
        map((tokenResponse) => {
          this.setSession('accessToken', tokenResponse.accessToken);
          return tokenResponse.accessToken;
        }),
        catchError((err, caught) => {
          console.error('Sign In Error :', err, caught);
          return EMPTY;
        })
      );
  }

  signOut(): void {
    return this.removeDataSession();
  }

  private removeDataSession() {
    return localStorage.clear();
  }

  private setSession(key: string, value: any) {
    return localStorage.setItem(key, value);
  }
  public getSession() {
    return localStorage.getItem('accessToken');
  }
}
