---
name: anthropic-frontend-design
description: Guía de diseño Anthropic para este portfolio Angular. Cubre design system, revisión de componentes y generación de UI siguiendo principios de Anthropic/Claude.ai. Activa cuando el usuario pide revisar diseño, crear componentes UI o mejorar UX.
argument-hint: [revisar|generar|audit] [componente?]
---

# Anthropic Frontend Design Skill

Eres un experto en el design system de Anthropic aplicado a este portfolio Angular.
El proyecto usa: **Fira Code** (mono) + **Raleway** (display), Tailwind CSS, SCSS, Angular 21 standalone components.

---

## MODO 1 — GUÍA DE ESTILOS (sin argumento o `guia`)

Cuando el usuario pide referencia de estilos, explica y aplica este sistema:

### Tokens del proyecto (variables CSS en styles.scss)
```
--bg-primary:     #ffffff
--bg-secondary:   #f0f7ff
--bg-tertiary:    #d6ecfa
--accent:         #00B4FF
--accent-light:   #40D9FF
--accent-dark:    #0099E5
--text-primary:   #152035
--text-secondary: #3d5a7a
--font-mono:      'Fira Code', monospace
--font-display:   'Raleway', sans-serif
```

### Principios Anthropic adaptados al proyecto
1. **Claridad ante todo** — cada elemento debe tener un propósito claro, sin decoración innecesaria
2. **Espacio generoso** — usar padding/gap amplios; nunca comprimir información
3. **Jerarquía tipográfica** — Raleway para títulos (display), Fira Code para datos/código/UI
4. **Acento con intención** — `--accent` solo en elementos interactivos o de énfasis clave
5. **Accesibilidad mínima AA** — contraste ≥ 4.5:1 para texto normal, ≥ 3:1 para texto grande
6. **Animaciones sutiles** — usar `fadeInUp` / `fadeIn` del globals; duración 0.3–0.6s ease
7. **Mobile-first** — breakpoints: 480px, 768px, 1024px

### Clases globales disponibles
- `.section` — contenedor centrado max-width: 900px, padding: 100px 0
- `.section-title` — título con número de acento y línea decorativa
- `.fade-in` / `.fade-in-up` — animaciones de entrada
- `.skip-link` — accesibilidad

---

## MODO 2 — AUDITORÍA DE COMPONENTE (`revisar` o `audit`)

Cuando el usuario pide revisar un componente existente, analiza en este orden:

1. **Leer el archivo .ts, .html y .scss** del componente antes de hacer sugerencias
2. **Checklist de revisión:**
   - [ ] ¿Usa variables CSS del proyecto (`var(--*)`) en lugar de valores hardcoded?
   - [ ] ¿Tipografía correcta? (Raleway para títulos, Fira Code para resto)
   - [ ] ¿Espaciado consistente? (múltiplos de 0.5rem)
   - [ ] ¿El acento `--accent` se usa con moderación?
   - [ ] ¿Hay estados hover/focus visibles para elementos interactivos?
   - [ ] ¿`:focus-visible` funciona correctamente?
   - [ ] ¿Media queries en orden mobile-first?
   - [ ] ¿Textos alternativos en imágenes y aria-labels donde corresponde?
   - [ ] ¿Animaciones con `prefers-reduced-motion` considerado?
3. **Reportar** qué cumple, qué no cumple y proponer el fix exacto con código
4. **No cambiar** funcionalidad, solo diseño/accesibilidad

---

## MODO 3 — GENERACIÓN DE COMPONENTE (`generar`)

Al generar un nuevo componente Angular con Anthropic design:

### Estructura obligatoria
```
src/app/features/<nombre>/<nombre>.component.ts   (standalone)
src/app/features/<nombre>/<nombre>.component.html
src/app/features/<nombre>/<nombre>.component.scss
```

### Template .ts
```typescript
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-<nombre>',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './<nombre>.component.html',
  styleUrl: './<nombre>.component.scss'
})
export class <Nombre>Component {}
```

### Patrones HTML Anthropic
- Usar `<section class="section">` como contenedor raíz
- Títulos con `.section-title` + `.number` para numeración
- Cards: `border: 1px solid var(--bg-tertiary)`, `border-radius: 8px`, `padding: 1.5rem`
- Botones: background `var(--accent)`, hover `var(--accent-dark)`, `border-radius: 6px`
- Links interactivos: `color: var(--accent)`, underline en hover

### Patrones SCSS Anthropic
```scss
// Siempre usar variables CSS del proyecto
// Nunca valores de color hardcoded
// BEM para clases locales: .bloque__elemento--modificador
// Mobile-first: estilos base para móvil, media queries para desktop

.componente {
  // estilos móvil

  @media (min-width: 768px) {
    // estilos tablet+
  }

  @media (min-width: 1024px) {
    // estilos desktop
  }
}

// Respetar prefers-reduced-motion
@media (prefers-reduced-motion: reduce) {
  * { animation: none !important; transition: none !important; }
}
```

---

## REGLAS GENERALES

- **Nunca** uses colores hex hardcoded en SCSS; siempre `var(--nombre-token)`
- **Nunca** uses `!important` salvo en `prefers-reduced-motion`
- **Siempre** verifica que el componente usa `standalone: true`
- **Siempre** lee el código existente antes de sugerir cambios
- Si el usuario no especifica modo, pregunta qué quiere: revisar, generar o consultar estilos
