import { Component, OnInit, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PortfolioService } from '../../core/services/portfolio.service';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.scss']
})
export class InicioComponent implements OnInit {

  private readonly service = inject(PortfolioService);

  nombre = signal('');
  titulo = signal('');
  descripcion = signal('');
  email = signal('');
  loaded = signal(false);

  ngOnInit(): void {
    this.service.getSobreMi().subscribe(data => {
      this.nombre.set(data.nombre);
      this.titulo.set(data.titulo);
      this.descripcion.set(data.descripcion);
      this.email.set(data.email);
      this.loaded.set(true);
    });
  }

  scrollTo(anchor: string): void {
    document.getElementById(anchor)?.scrollIntoView({ behavior: 'smooth' });
  }
}