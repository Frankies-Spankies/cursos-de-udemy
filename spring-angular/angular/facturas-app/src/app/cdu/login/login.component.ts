import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../model/usuario';
import swal from 'sweetalert2';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  titulo: String = "Login";
  usuario: Usuario;

  constructor(private authService: AuthServiceService, private router:Router) {
    this.usuario = new Usuario();
  }

  ngOnInit() {
    if (this.authService.isAuthenticated()) {
      this.router.navigate(['/cliente']);
      swal('Login', `Hola ${this.authService.usuario.username} ya has iniciado sesion`, 'info');
    }
  }

  login(): void {
    if (this.usuario.username == null || this.usuario.password == null) {
      swal('Error login', 'Credenciales vacias', "error");
    } else {
      this.authService.login(this.usuario).subscribe(resp => {
        this.router.navigate(['/inicio']);
        this.authService.guardaUsuario(resp.access_token);
        this.authService.guardaToken(resp.access_token);
        let user= this.authService.usuario;
        swal('Hola',user.username,'success');
      },
      err=>{
        if (err.status== 400) {
          swal('Error', 'usuario o contraseña invalidos', "error");
        }
      }
      );  

    }
  }

}
