import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import swal from 'sweetalert2';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  titulo: String = "Por favor Sign In!";
  usuario: Usuario;
  constructor(private authService: AuthService, private router: Router) {
    this.usuario = new Usuario();
  }

  ngOnInit() {
    if (this.authService.isAuthenticated()) {
      this.router.navigate(['/clientes']);
      swal.fire('Login', `Hola ${this.authService.usuario.nombre} ya has iniciado sesion con exito`, 'info');
    }
  }

  login(): void {
    if (this.usuario.username == null || this.usuario.password == null) {
      swal.fire('Error login', 'Username o password vacias', 'error');
      return;
    }
    this.authService.login(this.usuario).subscribe(
      resp => {
        this.router.navigate(['/clientes']);
        this.authService.guardarUsuario(resp.access_token);
        this.authService.guardarToken(resp.access_token);
        swal.fire('Login', `Hola ${this.authService.usuario.nombre} has iniciado sesion con exito`, 'success');
      },
      err => {
        if (err.status == 400) {
          swal.fire("Erroro Login", "Usuario o Clave incorrectas!", "error");
        }
      }
    );
  }



}
