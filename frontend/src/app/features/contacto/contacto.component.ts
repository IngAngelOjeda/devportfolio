import { Component, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { PortfolioService } from '../../core/services/portfolio.service';
import { ContactoRequest } from '../../core/models/portfolio.models';

@Component({
  selector: 'app-contacto',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './contacto.component.html',
  styleUrls: ['./contacto.component.scss']
})
export class ContactoComponent {

  private readonly service = inject(PortfolioService);

  estado = signal<'idle' | 'loading' | 'success' | 'error'>('idle');

  model: ContactoRequest = { nombre: '', email: '', mensaje: '' };

  enviar(form: NgForm): void {
    if (form.invalid) return;

    this.estado.set('loading');
    this.service.enviarContacto(this.model).subscribe({
      next: () => {
        this.estado.set('success');
        form.resetForm();
      },
      error: () => {
        this.estado.set('error');
      }
    });
  }
}