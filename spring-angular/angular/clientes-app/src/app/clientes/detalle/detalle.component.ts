import { Component, OnInit, Input } from '@angular/core';
import { Cliente } from '../cliente';
import { ClienteService } from '../cliente.service';
 import swal from 'sweetalert2';
import { HttpEventType } from '@angular/common/http';
import { ModalService } from './modal.service';
import { AuthService } from '../../usuarios/auth.service';

@Component({
  selector: 'app-detalle',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {

  titulo: String = 'Detalle del cliente'
  private archivo: File;
  @Input()cliente: Cliente;
  progreso:number;

  constructor(
    private clienteService: ClienteService,
    private modalService:ModalService,
    private authService:AuthService
  ) { }

  ngOnInit() {
  }

  clierraModal(){
    this.archivo=null;
    this.progreso=0;
    this.modalService.cerrarModal();
  }

  seleccionaArchivo(event) {
    this.archivo = event.target.files[0];
    this.progreso=0;
    if (this.archivo.type.indexOf('image') < 0) {
      swal.fire('Error', 'seleccionar archivo tipo imagen', 'error');
      this.archivo = null;
    }
    console.log(this.archivo)
  }

  subirArchivo() {

    if (!this.archivo) {
      swal.fire('Error', 'Debes seleccionar una foto', 'error');
    } else {
      this.clienteService.subirArchivo(this.archivo, this.cliente.id).subscribe(
        event => {
          if (event.type === HttpEventType.UploadProgress) {
            this.progreso=Math.round((event.loaded/event.total)*100);
          }
          if (event.type === HttpEventType.Response) {
            let response:any = event.body
            this.cliente = response.cliente;
            swal.fire('Foto subida Correctamente', `Imagen ${response.imagen}`, 'success');
            this.clienteService.notificarUpload.emit(this.cliente);

          }
        })

    } 

  }

}
