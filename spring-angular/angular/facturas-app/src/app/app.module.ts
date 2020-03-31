import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { HeaderComponent } from '../app/recursos/header/header.component';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './cdu/login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS  } from '@angular/common/http';
import { UinicioComponent } from './cdu/usuario/uinicio/uinicio.component';
import { AinicioComponent } from './cdu/admin/ainicio/ainicio.component';
import { InicioComponent } from './cdu/public/inicio/inicio.component';

import { FormsModule } from '@angular/forms';
import { AuthGuard } from './auth/guards/auth.guard';
import { RoleGuard } from './auth/guards/role.guard';
import { TokenInterceptor } from './auth/interceptors/token.interceptor';


const routes: Routes = [{ path: '', redirectTo: '/inicio', pathMatch:'full' },
                        { path: 'inicio', component: InicioComponent },
                        { path: 'uinicio', component: UinicioComponent, canActivate:[AuthGuard]},
                        { path: 'ainicio', component: AinicioComponent, canActivate:[AuthGuard,RoleGuard], data:{role:'ROLE_ADMIN'} },
                        { path: 'login', component: LoginComponent }
                      ];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    UinicioComponent,
    AinicioComponent,
    InicioComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    FormsModule
  ],
  providers: [ { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
