import { Component, OnInit, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PortfolioService } from '../../core/services/portfolio.service';
import { Proyecto } from '../../core/models/portfolio.models';

@Component({
  selector: 'app-proyectos',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './proyectos.component.html',
  styleUrls: ['./proyectos.component.scss']
})
export class ProyectosComponent implements OnInit {

  private readonly service = inject(PortfolioService);

  proyectos = signal<Proyecto[]>([]);

  ngOnInit(): void {
    this.service.getProyectos().subscribe(data => this.proyectos.set(data));
  }
}