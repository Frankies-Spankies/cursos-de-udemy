import { Component, OnInit } from '@angular/core';
import { PublicService } from '../../../service/public-service.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  mensaje:string="";
  constructor(private publicService:PublicService) { }

  ngOnInit() {
    this.publicService.inicio().subscribe(resp=>{
      console.log(resp.mensaje);
      this.mensaje=resp.mensaje;

    })
  }

}
