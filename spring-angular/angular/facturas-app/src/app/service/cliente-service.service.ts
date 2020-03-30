import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({providedIn: 'root'})
export class ClienteServiceService {
  constructor(private httpClient: HttpClient, private router:Router) { }

  private url: string = 'http://localhost:8080/api/clientes';

  
}