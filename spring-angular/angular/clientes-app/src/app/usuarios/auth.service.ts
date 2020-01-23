import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from './usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private _usuario:Usuario;
  private _token:string;


  constructor(private http:HttpClient) { }


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
  

  login(usuario:Usuario):Observable<any>{
    const url: string = 'http://localhost:8080/oauth/token';
    const credenciales = btoa('angularapp'+':'+'12345');
    const httpHeaders=new HttpHeaders(
      {
      'Content-Type':'application/x-www-form-urlencoded',
      'Authorization':'Basic '+credenciales
     }
    );
     let params = new URLSearchParams();//Nativo de JS no hay que importarlos
     params.set('grant_type', 'password');
     params.set('username', usuario.username);
     params.set('password', usuario.password);
     console.log(params.toString());
    return this.http.post<any>(url,params.toString(),{headers:httpHeaders}); //Convertir params a tipo string
  }

  guardarUsuario(accessToken:string):void{
    let payload = this.obtenerDatosToken(accessToken);
    this._usuario=new Usuario();
    this._usuario.username=payload.user_name;
    this._usuario.nombre=payload.nombre;
    this._usuario.email=payload.email;
    this._usuario.apellido=payload.apellido;
    this._usuario.roles=payload.authorities;
    sessionStorage.setItem('usuario',JSON.stringify(this._usuario));


  }

  guardarToken(accessToken:string):void{
    this._token=accessToken;
    sessionStorage.setItem('token',accessToken);

  }

  obtenerDatosToken(accessToken:string):any{
    if (accessToken != null) {
      return JSON.parse(atob(accessToken.split(".")[1]));
    }
    return null;

  }


  // Verifica si un usuario ya hizo login
  isAuthenticated():boolean{
    let payload=this.obtenerDatosToken(this.token);
    if (payload!=null && payload.user_name && payload.user_name.length>0) {
      return true
    }
    return false;
  }

  hasRole(role:string):boolean{
    if (this.usuario.roles.includes(role)) {
      return true;
    }
    return false;
  }

  logout():void {
    this._usuario=null;
    this._token=null;
    //sessionStorage.clear();
    sessionStorage.removeItem('usuario');
    sessionStorage.removeItem('token');
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
