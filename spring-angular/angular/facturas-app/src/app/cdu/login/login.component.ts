import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../model/usuario';
import swal from 'sweetalert2';
import { AuthServiceService } from 'src/app/service/auth-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  titulo:String="Login";
  usuario:Usuario;

  constructor(private authService:AuthServiceService) { 
    this.usuario=new Usuario();
  }

  ngOnInit() {
  }

  login():void{
    console.log(this.usuario);
    if (this.usuario.username==null || this.usuario.password==null ) {
      swal('Error login', 'Username o Password erroneos', "error");
    }else{
      this.authService.login(this.usuario).subscribe(resp=>console.log(resp));

    }
  }

}
