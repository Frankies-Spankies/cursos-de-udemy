import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HeaderComponentComponent } from './header-component/header-component.component';
import { ForComponentComponent } from './for-component/for-component.component';
import { ClientesComponent } from './clientes/clientes.component';
import { ClienteService } from './clientes/cliente.service';
import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from './clientes/form/form.component';
import { registerLocaleData } from '@angular/common';
import localeES from '@angular/common/locales/es';
import { PaginatorComponent } from './paginator/paginator.component';


registerLocaleData(localeES,'es');


const routes: Routes = [
  { path: '', redirectTo: '/clientes', pathMatch: 'full' },
  { path: 'directivas', component: ForComponentComponent },
  { path: 'clientes', component: ClientesComponent },
  { path: 'clientes/page/:page', component: ClientesComponent },
  { path: 'clientes/form', component: FormComponent},
  { path: 'clientes/form/:id', component: FormComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponentComponent,
    ForComponentComponent,
    ClientesComponent,
    FormComponent,
    PaginatorComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule, 
    RouterModule.forRoot(routes),
    FormsModule
  ],
  providers: [
    ClienteService,
    {provide: LOCALE_ID, useValue: 'es'} 

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
