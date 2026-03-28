import { Component, OnInit, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PortfolioService } from '../../core/services/portfolio.service';
import { Experiencia } from '../../core/models/portfolio.models';

@Component({
  selector: 'app-experiencia',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './experiencia.component.html',
  styleUrls: ['./experiencia.component.scss']
})
export class ExperienciaComponent implements OnInit {

  private readonly service = inject(PortfolioService);

  experiencias = signal<Experiencia[]>([]);
  activeIndex = signal(0);

  ngOnInit(): void {
    this.service.getExperiencias().subscribe(data => this.experiencias.set(data));
  }

  selectTab(index: number): void {
    this.activeIndex.set(index);
  }

  get active(): Experiencia | null {
    const list = this.experiencias();
    return list[this.activeIndex()] ?? null;
  }
}