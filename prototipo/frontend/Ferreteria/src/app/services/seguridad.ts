import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Seguridad {
  
  private baseUrl = 'http://localhost:8080/api/security';

  constructor(private http: HttpClient) {}

  login(credenciales: { username: string; password: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, credenciales);
  }

  getMenu(rolId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/menu/${rolId}`);
  }

}
