import { Injectable } from '@angular/core';
import {
    HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';

import { Observable } from 'rxjs';
import { AuthServiceService } from '../../service/auth-service.service';


/* Clase utilizada para Intereceptar los request
Por defecto el Content-Type que envia es aplication/json */

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

    constructor(
        private authService: AuthServiceService
    ) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        let token = this.authService.token;
        if (token!=null) {
            const authReq = req.clone({
                headers: req.headers.set('Authorization', 'Bearer '+ token )
              });
              return next.handle(authReq);

        }
        return next.handle(req);
    }
}