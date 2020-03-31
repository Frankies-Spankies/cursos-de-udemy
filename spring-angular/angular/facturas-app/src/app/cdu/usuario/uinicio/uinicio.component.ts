import { Component, OnInit } from '@angular/core';
import { UsuarioService} from '../../../service/usuario-service.service';

@Component({
  selector: 'app-uinicio',
  templateUrl: './uinicio.component.html',
  styleUrls: ['./uinicio.component.css']
})
export class UinicioComponent implements OnInit {

  mensaje:string;
  constructor(private usuarioService:UsuarioService) { }

  ngOnInit() {
    this.usuarioService.uinicio().subscribe(resp=>{
      this.mensaje=resp.mensaje;

    })
  }

}
