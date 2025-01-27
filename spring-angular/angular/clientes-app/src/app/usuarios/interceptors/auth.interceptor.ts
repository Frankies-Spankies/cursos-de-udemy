import { Injectable } from '@angular/core';
import {
    HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import swal from 'sweetalert2';


/* Clase utilizada para Intereceptar los request
Por defecto el Content-Type que envia es aplication/json */

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(
        private authService: AuthService,
        private router:Router
    ) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {


        return next.handle(req).pipe(
            catchError(e=>{
                if(e.status===401 ){
                    if (this.authService.isAuthenticated()) { //En caso de que haya expirado el token
                      this.authService.logout();
                    }
                    this.router.navigate(['/login']);
              
                  }
                  if(e.status===403){//Acceso denegado
                    swal.fire('No Autorizado',`Hola ${this.authService.usuario.nombre} no tines acceso a este recurso`, 'warning')
                     this.router.navigate(['/clientes']);
                   }
                return throwError(e);
            }),
        );
    }
}