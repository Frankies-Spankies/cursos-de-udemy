import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { ClienteComponent } from './cliente/cliente.component';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [{ path: '', redirectTo: '/cliente', pathMatch:'full' },
                        { path: 'cliente', component: ClienteComponent }];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ClienteComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
