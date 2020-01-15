import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente';
import { ClienteService } from '../cliente.service';
import { ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-detalle',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {

  titulo:String='Detalle del cliente'
  private archivo:File;
  cliente:Cliente

  constructor(
    private clienteService:ClienteService,
    private activatedRoute:ActivatedRoute
    ) { }

  ngOnInit() {

    this.activatedRoute.params.subscribe(params=>{
      let id:number=params['id'];
      if (id) {
        this.clienteService.getCliente(id+'').subscribe(cliente=>{
          this.cliente=cliente;
        });

      }

    });
  }

  seleccionaArchivo(event){
     this.archivo=event.target.files[0];
     if (this.archivo.type.indexOf('image')<0) {
       swal.fire('Error','seleccionar archivo tipo imagen', 'error');
       this.archivo=null;
     }
     console.log(this.archivo)
  }

  subirArchivo(){

    if (!this.archivo) {
      swal.fire('Error','Debes seleccionar una foto','error');
    }else{
      this.clienteService.subirArchivo(this.archivo,this.cliente.id).subscribe(cliente=>{
        this.cliente=cliente;
        swal.fire('Foto subida Correctamente', `Imagen ${cliente.imagen}`, 'success');
      })

    }
  }

}
