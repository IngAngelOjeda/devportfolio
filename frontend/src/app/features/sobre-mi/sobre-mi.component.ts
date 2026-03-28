import { Component, OnInit, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PortfolioService } from '../../core/services/portfolio.service';
import { SobreMi } from '../../core/models/portfolio.models';

@Component({
  selector: 'app-sobre-mi',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './sobre-mi.component.html',
  styleUrls: ['./sobre-mi.component.scss']
})
export class SobreMiComponent implements OnInit {

  private readonly service = inject(PortfolioService);

  data = signal<SobreMi | null>(null);

  ngOnInit(): void {
    this.service.getSobreMi().subscribe(d => this.data.set(d));
  }
}