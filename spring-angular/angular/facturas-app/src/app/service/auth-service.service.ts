import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../model/usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private _usuario:Usuario;
  private _token:string;

  public get usuario() : Usuario {
    if (this._usuario!=null) {
      return this._usuario
    }else if (this._usuario==null && sessionStorage.getItem('usuario')) {
      this._usuario=JSON.parse(sessionStorage.getItem('usuario')) as Usuario;
      return this._usuario;
    }
    return new Usuario();
  }

  public get token() : string {
    if (this._token!=null) {
      return this._token
    }else if (this._token==null && sessionStorage.getItem('token')) {
      this._token=sessionStorage.getItem('token');
      return this._token;
    }
    return null;
  }

  hasRole(role:string):boolean{
    if (this.usuario.roles.includes(role)) {
      return true;
    }
    return false;
  }

  guardaToken(token) {
    this._token=token;
    sessionStorage.setItem('token',token);
  }
  
  guardaUsuario(token) {
    this._usuario=new Usuario();
    let payload=this.obtenerDatosToken(token);
    this._usuario.username=payload.user_name;
    this._usuario.roles=payload.authorities;
    sessionStorage.setItem('usuario',JSON.stringify(this._usuario));
  }

  obtenerDatosToken(token){
    if (token!=null) {
      return JSON.parse(atob(token.split('.')[1]));
    }
    return null;
  }


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
  logout():void {
    this._usuario=null;
    this._token=null;
    //sessionStorage.clear();
    sessionStorage.removeItem('usuario');
    sessionStorage.removeItem('token');
  }

  isAuthenticated():boolean{
    let payload=this.obtenerDatosToken(this.token);
    if (payload!=null && payload.user_name && payload.user_name.length>0) {
      return true
    }
    return false;
  }
  isTokenExpirado():boolean{
    let payload = this.obtenerDatosToken(this.token);
    let now = new Date().getTime() / 1000;

    if (payload.exp < now) {
      return true;
    }
    return false;

}
}
