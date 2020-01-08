import { Component, OnInit } from '@angular/core';
import { ClienteService } from './cliente.service';
import { Cliente } from './cliente';


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

}
