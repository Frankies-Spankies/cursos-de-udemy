import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import swal from 'sweetalert2';
import { AuthServiceService } from '../../service/auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(
    private authService:AuthServiceService,
    private router:Router
    ){}
    

  // Pasa por parametro la el role  
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      let role = next.data['role'] as string;
      console.log(role);

      if (!this.authService.isAuthenticated()) {
        this.router.navigate(['/login']);
        return false;
      }

      if (this.authService.hasRole(role)) {
        return true;
      }
      swal('No Autorizado',`Hola ${this.authService.usuario.username} no tines acceso a este recurso`, 'warning')
      this.router.navigate(['/inicio']);
      return false;
  }
  
}
