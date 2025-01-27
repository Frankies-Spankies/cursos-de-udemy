import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente';
import { ClienteService } from '../cliente.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2'
import { Region } from '../region';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  private cliente: Cliente = new Cliente();
  private titulo: string = "Crear Cliente";
  private errores: string[] = [];
  private regiones:Region[];

  constructor(
    private clienteService: ClienteService,
    private router: Router,
    private activatedRoute: ActivatedRoute

  ) { }

  ngOnInit() {
    this.mostrarCliente();
    this.clienteService.getRegiones().subscribe(regiones=>{
      this.regiones=regiones;
    });
  }

  crearCliente(): void {
    this.clienteService.crearCliente(this.cliente).subscribe(
      resp => {
        this.router.navigate(['/clientes']);
        swal.fire('Nuevo cliente', `Cliente ${resp.cliente.nombre}`, 'success');
      },
      error => {
        this.errores = error.error;
        
        console.log(error.error);
      });
  }

  mostrarCliente(): void {
    let id;
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      if (id) {
        this.clienteService.getCliente(id).subscribe(cliente => {
          this.cliente = cliente;
          console.log(this.cliente);
        });
      }
    });
  }

  actualizarClienteForm(): void {
    this.clienteService.actualizaCliente(this.cliente).subscribe(
      resp => {
        this.router.navigate(['/clientes']);
        swal.fire('Cliente actualizado', `El cliente ${this.cliente.nombre} fue actualizaco`, 'success');
      },
      error => {
        this.errores = error.error;
        console.log(error.error);
      });
  }

  tieneRegion(selectRegion, clienteRegion):boolean{
    if (selectRegion === undefined && clienteRegion === undefined ) {
     return true; 
    }
    return selectRegion === null || clienteRegion === null ||selectRegion === undefined || clienteRegion === undefined ? false: selectRegion.nombre==clienteRegion.nombre;
  }

}
