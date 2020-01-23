import { Injectable, EventEmitter } from '@angular/core';
import { Cliente } from './cliente';
// import { CLIENTES } from './clientes.json';
import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpRequest, HttpEvent } from '@angular/common/http';
import swal from 'sweetalert2';
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
  _notificarUpload=new EventEmitter<any>();

  constructor(private http: HttpClient, private router: Router, private authService:AuthService) { }

  private agregarAuthorization(){
    let token = this.authService.token;
    if (token!=null) {
      this.httpHeaders=this.httpHeaders.append('Authorization', 'Bearer '+ token);
    }
    return this.httpHeaders;
  }



  private isNoAtorizado(e):boolean{
     if(e.status===401 || e.status===403){
       this.router.navigate(['/login']);
       return true;
     }
     return false;
  }





  get notificarUpload():EventEmitter<any>{
    return this._notificarUpload;
  }


  getRegiones():Observable<Region[]>{
    return this.http.get<Region[]>(this.url+'/regiones', { headers: this.agregarAuthorization()}).pipe(
      catchError( e => {//Catch erroor siempre tiene que regresar el error
        this.isNoAtorizado(e);
        return throwError(e);
      }),
    );
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
    return this.http.post<any>(this.url, cliente,  { headers: this.agregarAuthorization()}).pipe(
      catchError(e => {
        if (this.isNoAtorizado(e)) {
          return throwError(e);
        }

        if (e.status === 400) {
          return throwError(e);
        }
        console.log(e);
        swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  getCliente(id: string): Observable<Cliente> {
    return this.http.get<Cliente>(this.url + `/${id}`,{ headers: this.agregarAuthorization()}).pipe(
      catchError(e => {
        if (this.isNoAtorizado(e)) {
          return throwError(e);
        }
        this.router.navigate(['/clientes']);
        console.log(e);
        swal.fire('Error al obtener cliente :', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  actualizaCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(this.url + `/${cliente.id}`, cliente, { headers: this.agregarAuthorization()}).pipe(
      catchError(e => {

        if (this.isNoAtorizado(e)) {
          return throwError(e);
        }

        if (e.status === 400) {
          return throwError(e);
        }
        console.log(e);
        swal.fire('Error actualizar el cliente:', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  borrarCliente(id: number): Observable<Cliente> {
    return this.http.delete<Cliente>(this.url + `/${id}`, { headers: this.agregarAuthorization()}).pipe(//no eran nescesarias las cabeceras
      catchError(e => {
        if (this.isNoAtorizado(e)) {
          return throwError(e);
        }

        console.log(e);
        swal.fire('Error eliminar el cliente:', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  subirArchivo(archivo: File, id): Observable<HttpEvent<{}>> {
    let formData = new FormData();
    formData.append("archivo", archivo);
    formData.append("id", id);

    let httpHeaders=new HttpHeaders(); //Ser crea nueva instancia por que el tipo del body es un form no un aplication/json
    let token = this.authService.token;
    if (token!=null) {
      httpHeaders  = httpHeaders.append('Authorization', 'Bearer '+ token); //por que retornar una nueva instancia y esa es la que se quiere ocupar
    }


    const req = new HttpRequest('POST', this.url + '/upload', formData, {
      reportProgress: true,
      headers: httpHeaders
    });

    return this.http.request(req).pipe(
      catchError( e => {
        this.isNoAtorizado(e);
        return throwError(e);
      }),
    );
  }

  }
