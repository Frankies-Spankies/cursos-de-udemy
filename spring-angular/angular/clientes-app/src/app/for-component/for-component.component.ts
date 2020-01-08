import { Component, OnInit } from '@angular/core';
import { not } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-for-component',
  templateUrl: './for-component.component.html',
  styleUrls: ['./for-component.component.css']
})
export class ForComponentComponent  {


  lenguajes: string [] = ['Java', 'JavaScript', 'Python'];
  habilitar: boolean = true;


  notHabilitar(){
    this.habilitar= !this.habilitar;
  }
  constructor() { }

}
