import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from '../app/recursos/header/header.component';
import { ClienteComponent } from '../app/cdu/cliente/cliente.component';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './cdu/login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

const routes: Routes = [{ path: '', redirectTo: '/cliente', pathMatch:'full' },
                        { path: 'cliente', component: ClienteComponent },
                        { path: 'login', component: LoginComponent }
                      ];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ClienteComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
