import { Injectable } from '@angular/core';
import { Cliente } from './cliente';
import { CLIENTES } from './clientes.json';
import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import swal from 'sweetalert2';
import { Router } from '@angular/router';
import { formatDate, registerLocaleData } from '@angular/common';



@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private url: string = 'http://localhost:8080/api/clientes';
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient, private router:Router) { }

  getClientes(pages:number): Observable<Cliente[]> {
    //return of(CLIENTES); 

    //Otra forma de castear la respuesta al tipo Cilentes
    // return this.http.get(this.url).pipe(
    //   map( response => response as Cliente[])
    // );

    return this.http.get<Cliente[]>(this.url+'/page/'+pages).pipe(
      map((resp:any)=>{
        let clientes = resp.content as Cliente[];
        clientes.map(cliente=>{

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
    return this.http.post<any>(this.url, cliente, { headers: this.httpHeaders }).pipe(
      catchError(e => {
        if (e.status===400) {
          return throwError(e);
        }
        console.log(e);
        swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  getCliente(id: string): Observable<Cliente> {
    return this.http.get<Cliente>(this.url + `/${id}`).pipe(
      catchError(e => {
        this.router.navigate(['/clientes']); 
        console.log(e);
        swal.fire('Error al obtener cliente :', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  actualizaCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(this.url + `/${cliente.id}`, cliente, { headers: this.httpHeaders }).pipe(
      catchError(e => {
        if (e.status===400) {
          return throwError(e);
        }
        console.log(e);
        swal.fire('Error actualizar el cliente:', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  borrarCliente(id: number): Observable<Cliente> {
    return this.http.delete<Cliente>(this.url + `/${id}`, { headers: this.httpHeaders }).pipe(
      catchError(e => {
        console.log(e);
        swal.fire('Error eliminar el cliente:', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }
}
