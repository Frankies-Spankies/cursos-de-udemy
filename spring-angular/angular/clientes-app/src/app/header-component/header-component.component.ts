import { Component, OnInit } from '@angular/core';
import { AuthService } from '../usuarios/auth.service';
import swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-component',
  templateUrl: './header-component.component.html',
  styleUrls: ['./header-component.component.css']
})
export class HeaderComponentComponent implements OnInit {

  constructor(private authService:AuthService, private router:Router) { }

  ngOnInit() {
  }
  logout():void{
    let usename=this.authService.usuario.nombre;
    this.authService.logout();
    this.router.navigate(['/login']);
    swal.fire('Logout',`Hola ${usename} has cerrado sesion con exito`, 'success' );
  }
}
