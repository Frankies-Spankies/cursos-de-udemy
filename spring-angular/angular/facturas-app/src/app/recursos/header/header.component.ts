import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../../service/auth-service.service';
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private authService:AuthServiceService, private router:Router) { }

  ngOnInit() {  
  }

  logout():void{
    let username=this.authService.usuario.username;
    this.authService.logout();
    this.router.navigate(['/login']);
    swal('Logout',`Hola ${username} has cerrado sesion con exito`, 'success' );
  }




}
