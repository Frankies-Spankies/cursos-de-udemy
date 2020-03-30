import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../model/usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {



  constructor(private http: HttpClient) { }

  login(usuario: Usuario): Observable<any> {
    const url: string = 'http://localhost:8080/oauth/token';
    const credencialesapp: string = btoa('angularapp' + ':' + '12345');
    const httpHeaders: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization':'Basic '+credencialesapp });
    let params= new URLSearchParams();
    params.set('grant_type','password');
    params.set('username',usuario.username);
    params.set('password',usuario.password);

    return this.http.post<any>(url, params.toString(), { headers:httpHeaders });

  }
}
