import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../service/admin-service.service';

@Component({
  selector: 'app-ainicio',
  templateUrl: './ainicio.component.html',
  styleUrls: ['./ainicio.component.css']
})
export class AinicioComponent implements OnInit {

  mensaje:string;
  constructor(private adminService:AdminService) { }

  ngOnInit() {
    this.adminService.ainicio().subscribe(resp=>{
      this.mensaje=resp.mensaje;

    })
  }

}
