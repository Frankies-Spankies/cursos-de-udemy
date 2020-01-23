import { Injectable, EventEmitter } from '@angular/core';
import { Cliente } from './cliente';
// import { CLIENTES } from './clientes.json';
import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpRequest, HttpEvent } from '@angular/common/http';
import { Router } from '@angular/router';
import { formatDate, registerLocaleData } from '@angular/common';
import { Region } from './region';
import { AuthService } from '../usuarios/auth.service';



@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private url: string = 'http://localhost:8080/api/clientes';
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  _notificarUpload = new EventEmitter<any>();

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) { }
  //=================== PARTE REMOVIDA AL INTERCEPTOR AUTHINTERCEPTOR  y TOKENINTERCEPTOR ===================


  // private agregarAuthorization(){
  //   let token = this.authService.token;
  //   if (token!=null) {
  //     this.httpHeaders=this.httpHeaders.append('Authorization', 'Bearer '+ token);
  //   }
  //   return this.httpHeaders;
  // }




  // private isNoAtorizado(e):boolean{//No autenticado
  //   if(e.status===401 ){
  //     if (this.authService.isAuthenticated()) { //En caso de que haya expirado el token
  //       this.authService.logout();
  //     }
  //     this.router.navigate(['/login']);

  //     return true;
  //   }
  //   if(e.status===403){//Acceso denegado
  //     swal.fire('No Autorizado',`Hola ${this.authService.usuario.nombre} no tines acceso a este recurso`, 'warning')
  //      this.router.navigate(['/clientes']);
  //      return true;
  //    }
  //    return false;
  // }

  //=================== PARTE REMOVIDA AL INTERCEPTOR AUTH ===================






  get notificarUpload(): EventEmitter<any> {
    return this._notificarUpload;
  }


  getRegiones(): Observable<Region[]> {
    return this.http.get<Region[]>(this.url + '/regiones'/* , { headers: this.agregarAuthorization()} */)
  }

  getClientes(pages: number): Observable<Cliente[]> {
    //return of(CLIENTES); 

    //Otra forma de castear la respuesta al tipo Cilentes
    // return this.http.get(this.url).pipe(
    //   map( response => response as Cliente[])
    // );

    return this.http.get<Cliente[]>(this.url + '/page/' + pages).pipe(
      map((resp: any) => {
        let clientes = resp.content as Cliente[];
        clientes.map(cliente => {

          //cliente.createAt=formatDate(cliente.createAt, 'fullDate','es');
          return cliente;
        });
        return resp;
      })
    );
  }


  //====================
  //Dejar el tipo any tambien en el update
  //====================
  crearCliente(cliente: Cliente): Observable<any> {
    return this.http.post<any>(this.url, cliente/* ,  { headers: this.agregarAuthorization()} */).pipe(
      catchError(e => {


        if (e.status === 400) {
          return throwError(e);
        }

        if (e.error.mensaje) {
          console.log(e);
        } 
        return throwError(e);
      })
    );
  }

  getCliente(id: string): Observable<Cliente> {
    return this.http.get<Cliente>(this.url + `/${id}`/* ,{ headers: this.agregarAuthorization()} */).pipe(
      catchError(e => {
        if (e.status != 401 && e.error.mensaje) {
          this.router.navigate(['/clientes']);
          console.log(e);

        }
        return throwError(e);
      })
    );
  }

  actualizaCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(this.url + `/${cliente.id}`, cliente/* , { headers: this.agregarAuthorization()} */).pipe(
      catchError(e => {



        if (e.status === 400) {
          return throwError(e);
        }

        if (e.error.mensaje) {
          console.log(e);
        }        
        return throwError(e);
      })
    );
  }

  borrarCliente(id: number): Observable<Cliente> {
    return this.http.delete<Cliente>(this.url + `/${id}`/* , { headers: this.agregarAuthorization()} */).pipe(//no eran nescesarias las cabeceras
      catchError(e => {


        if (e.error.mensaje) {
          console.log(e);
        }
        return throwError(e);
      })
    );
  }

  subirArchivo(archivo: File, id): Observable<HttpEvent<{}>> {
    let formData = new FormData();
    formData.append("archivo", archivo);
    formData.append("id", id);

    /*     let httpHeaders=new HttpHeaders(); //Ser crea nueva instancia por que el tipo del body es un form no un aplication/json
        let token = this.authService.token;
        if (token!=null) {
          httpHeaders  = httpHeaders.append('Authorization', 'Bearer '+ token); //por que retornar una nueva instancia y esa es la que se quiere ocupar
        } */


    const req = new HttpRequest('POST', this.url + '/upload', formData, {
      reportProgress: true/* ,
      headers: httpHeaders */
    });

    return this.http.request(req);
  }

}
