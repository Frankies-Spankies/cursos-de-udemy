import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UsuarioServiceService {

  constructor(http:HttpClient) { }
  private url: string = 'http://localhost:8080/api/clientes';

}
