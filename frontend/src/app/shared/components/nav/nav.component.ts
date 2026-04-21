import { Component, HostListener, signal, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PortfolioService } from '../../../core/services/portfolio.service';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {

  private readonly portfolioService = inject(PortfolioService);

  scrolled = signal(false);
  activeSection = signal('inicio');
  email = signal('stacklabcontacto@gmail.com');

  readonly navLinks = [
    { label: 'Quiénes somos', number: '01', anchor: 'sobre-mi' },
    { label: 'Servicios',     number: '02', anchor: 'experiencia' },
    { label: 'Proyectos',     number: '03', anchor: 'proyectos' },
    { label: 'Contacto',      number: '04', anchor: 'contacto' },
  ];

  ngOnInit(): void {
    this.portfolioService.getSobreMi().subscribe(data => {
      this.email.set(data.email);
    });
    this.setupScrollSpy();
  }

  @HostListener('window:scroll')
  onScroll(): void {
    this.scrolled.set(window.scrollY > 50);
  }

  scrollTo(anchor: string): void {
    document.getElementById(anchor)?.scrollIntoView({ behavior: 'smooth' });
  }

  private setupScrollSpy(): void {
    const sections = ['inicio', 'sobre-mi', 'experiencia', 'proyectos', 'contacto'];
    const observer = new IntersectionObserver(
      entries => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            this.activeSection.set(entry.target.id);
          }
        });
      },
      { threshold: 0.3 }
    );
    sections.forEach(id => {
      const el = document.getElementById(id);
      if (el) observer.observe(el);
    });
  }
}