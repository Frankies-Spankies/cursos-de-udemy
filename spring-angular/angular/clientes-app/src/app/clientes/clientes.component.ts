import { Component, OnInit } from '@angular/core';
import { ClienteService } from './cliente.service';
import { Cliente } from './cliente';
import swal from 'sweetalert2'


@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {

  clientes: Cliente[];
  
  
  //============EQUIVALENTE DE INYECTAR UN SERVIOCIO============

  // constructor( clienteService : ClienteService) { 
  //   this.clienteService=clienteService;
  // }
 
  constructor( private clienteService : ClienteService) { 
  }

  ngOnInit() {
    this.clienteService.getClientes().subscribe(clientes=>{
      this.clientes=clientes;
    });
  }


  eliminarCliente(cliente:Cliente):void{
    const swalWithBootstrapButtons = swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    
    swalWithBootstrapButtons.fire({
      title: 'Estas seguro de eliminar al cliente',
      text: `${cliente.nombre}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Borrar',
      cancelButtonText: 'Cancelar',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.clienteService.borrarCliente(cliente.id).subscribe(resp=>{
          this.clientes=this.clientes.filter(cli=>cli!=cliente);
        });
        swalWithBootstrapButtons.fire(
          '¿Eliminado!',
          'Cliente eliminado con exito.',
          'success'
        )
      } 
    })
  }




}
