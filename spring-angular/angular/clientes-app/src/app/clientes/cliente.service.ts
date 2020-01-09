import { Injectable } from '@angular/core';
import { Cliente } from './cliente';
import { CLIENTES } from './clientes.json';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private url:string='http://localhost:8080/api/clientes';

  constructor(private http:HttpClient) { }

  getClientes():Observable<Cliente[]>{
    //return of(CLIENTES); 
    
    //Otra forma de catear la respuesta al tipo Cilentes
    // return this.http.get(this.url).pipe(
    //   map( response => response as Cliente[])
    // );

    return this.http.get<Cliente[]>(this.url);
  }


}
