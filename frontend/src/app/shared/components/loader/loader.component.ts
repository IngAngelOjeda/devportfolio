import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-loader',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="loader-overlay" *ngIf="visible" aria-live="polite" aria-label="Cargando">
      <div class="loader-content">
        <img src="/stackLab.png" alt="StackLab" class="loader-logo" />
        <div class="loader-bar">
          <div class="loader-bar-fill"></div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .loader-overlay {
      position: fixed;
      inset: 0;
      background: #ffffff;
      display: flex;
      align-items: center;
      justify-content: center;
      z-index: 9999;
      animation: fadeOut 0.4s ease 2s forwards;
    }

    .loader-content {
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: 2rem;
    }

    .loader-logo {
      width: 200px;
      animation: pulseLogo 1.5s ease-in-out infinite;
    }

    .loader-bar {
      width: 200px;
      height: 3px;
      background: rgba(0, 180, 255, 0.15);
      border-radius: 3px;
      overflow: hidden;
    }

    .loader-bar-fill {
      height: 100%;
      width: 0%;
      background: linear-gradient(90deg, #40D9FF, #00B4FF, #0099E5);
      border-radius: 3px;
      animation: fillBar 1.8s ease forwards;
    }

    @keyframes pulseLogo {
      0%, 100% { opacity: 1;   transform: scale(1); }
      50%       { opacity: 0.8; transform: scale(1.03); }
    }

    @keyframes fillBar {
      0%   { width: 0%; }
      60%  { width: 80%; }
      100% { width: 100%; }
    }

    @keyframes fadeOut {
      to { opacity: 0; pointer-events: none; }
    }
  `]
})
export class LoaderComponent {
  @Input() visible = true;
}