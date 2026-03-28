import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavComponent } from './shared/components/nav/nav.component';
import { LoaderComponent } from './shared/components/loader/loader.component';
import { InicioComponent } from './features/inicio/inicio.component';
import { SobreMiComponent } from './features/sobre-mi/sobre-mi.component';
import { ExperienciaComponent } from './features/experiencia/experiencia.component';
import { ProyectosComponent } from './features/proyectos/proyectos.component';
import { ContactoComponent } from './features/contacto/contacto.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    NavComponent,
    LoaderComponent,
    InicioComponent,
    SobreMiComponent,
    ExperienciaComponent,
    ProyectosComponent,
    ContactoComponent
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App implements OnInit {

  loading = signal(true);

  ngOnInit(): void {
    setTimeout(() => this.loading.set(false), 2000);
  }
}