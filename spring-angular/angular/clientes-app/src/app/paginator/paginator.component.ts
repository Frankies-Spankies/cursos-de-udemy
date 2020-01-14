import { Component, OnInit, Input, OnChanges, SimpleChange, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.css']
})
export class PaginatorComponent implements OnInit, OnChanges {

  @Input() paginador: any;

  paginas: number[];

  //RANGOS DE PAGINACION 
  desde: number;
  hasta: number;
  //Numero de pagina a partir del cual va a EMPEZAR de crecer el arreglo
  cambioMin: number;
  //Numero de pagina a partir del cual va a PARAR de crecer el arreglo
  cambioMax: number;
  //Numero minimo de paginas con el que el panador funciona sin cambiar
  minPaginas: number;


  //Fucion que camlula el tamaño del arreglo de paginas siendo el 
  // TAMAÑO MAXIMO = minPaginas+(cambioMax-cambioMin-1)

  tamañoArreglo(): number {
    this.desde = Math.min(Math.max(1, this.paginador.number - this.cambioMin), this.paginador.totalPages - this.minPaginas);
    this.hasta = Math.max(Math.min(this.paginador.totalPages , this.paginador.number + this.cambioMin), this.cambioMax);
    return this.hasta - this.desde
  }


  //Constructor se ejecuta antes del ngOnInit
  constructor() {
    this.cambioMin = 4;
    this.cambioMax = 6;
    this.minPaginas = 5;

  }

  ngOnInit() { 
    this.calcultaTamañoPaginador();
  }
  
  ngOnChanges(changes:SimpleChanges):void {
    let cambioPaginador=changes['paginador'];
    if (cambioPaginador.previousValue) {
      this.calcultaTamañoPaginador();
    }

  }
  
  
  calcultaTamañoPaginador(){
    if (this.paginador.totalPages < this.minPaginas) {
      this.paginas = new Array(this.paginador.totalPages).fill(0).map((valor, indice) => {
        return indice + 1;
      });
  
    } else {
      this.paginas = new Array(this.tamañoArreglo()).fill(0).map((valor, indice) => {
        return indice + this.desde;
      });
  
    }

  }

}
