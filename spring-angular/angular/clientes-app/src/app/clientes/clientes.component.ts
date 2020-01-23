import { Component, OnInit } from '@angular/core';
import { ClienteService } from './cliente.service';
import { Cliente } from './cliente';
import swal from 'sweetalert2'
import { tap } from 'rxjs/operators';
// import { CLIENTES } from './clientes.json';
import { ActivatedRoute } from '@angular/router';
import { ModalService } from './detalle/modal.service';
import { AuthService } from '../usuarios/auth.service';


@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {

  clientes: Cliente[];
  paginador: any;
  clienteSeleccionado: Cliente;


  //============EQUIVALENTE DE INYECTAR UN SERVIOCIO============

  // constructor( clienteService : ClienteService) { 
  //   this.clienteService=clienteService;
  // }

  constructor(
    private clienteService: ClienteService,
    private activatedRoute: ActivatedRoute,
    private modalService: ModalService,
    private authService:AuthService
  ) { }


  //==========================

  //Mediante el operador tap unicamente se obtienen los datos de la respuesta, pero no se modifican aun que se modifique en el operador
  //El operador map si hace un cambio en la rspuesta
  //El operador pipe sirve para poner varios operadores que reciben funciones cuyo parametro es la respuesta de una peticiion, 
  //o lo que regrea un observable 

  //==========================

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      //El operador suma caste un String a Number
      let page: number = params['page'];
      if (!page) {
        page = 0;
      }
      this.clienteService.getClientes(page).pipe(
        tap((resp: any) => {
          this.clientes = resp.content;
          this.paginador = resp;
        })
      ).subscribe();
    });



    this.clienteService._notificarUpload.subscribe(cliente => {
      this.clientes = this.clientes.map(clienteItera => {
        if (clienteItera.id === cliente.id) {
          clienteItera.imagen = cliente.imagen;
        }
        return clienteItera;
      });
    });

  }



  disparaModal(cliente: Cliente) {
    this.clienteSeleccionado = cliente;
    this.modalService.abrirModal();
  }


  eliminarCliente(cliente: Cliente): void {
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
        this.clienteService.borrarCliente(cliente.id).subscribe(
          resp => {
            this.clientes = this.clientes.filter(cli => cli != cliente);
            swalWithBootstrapButtons.fire(
              '¿Eliminado!',
              'Cliente eliminado con exito.',
              'success'
            );
          },
          err=>{
            swalWithBootstrapButtons.fire(
              'Error',
              'Error al eliminar el cliente',
              'error'
            );
          }
          );
      }
    })
  }




}
