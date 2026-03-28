# CLAUDE.md — Portfolio Frontend (Angular)

## Visión general del proyecto
Portfolio personal estilo desarrollador, inspirado en el diseño de Brittany Chiang.
SPA en Angular que consume una REST API de Spring Boot (puerto 8080).
Todo el contenido está en **español**.

---

## Stack tecnológico
- **Framework:** Angular 17+ (standalone components, signals)
- **Estilos:** Tailwind CSS + SCSS custom para animaciones
- **Fuentes:** Google Fonts — `Fira Code` (monospace, para etiquetas) + `Raleway` (display, para títulos)
- **HTTP:** Angular HttpClient con interceptors
- **Routing:** Angular Router (hash-less, con lazy loading)
- **Animaciones:** CSS keyframes + Angular Animations API

---

## Paleta de colores (CSS variables)
```css
--bg-primary:    #0a192f;   /* Fondo principal */
--bg-secondary:  #112240;   /* Tarjetas, hover */
--bg-tertiary:   #1d3461;   /* Bordes sutiles */
--accent:        #64ffda;   /* Verde menta — acento principal */
--text-primary:  #ccd6f6;   /* Texto principal */
--text-secondary:#8892b0;   /* Texto secundario */
--text-white:    #e6f1ff;   /* Headings grandes */
--font-mono:     'Fira Code', monospace;
--font-display:  'Raleway', sans-serif;
```

---

## Estructura del proyecto
```
src/
├── app/
│   ├── core/
│   │   ├── services/
│   │   │   └── portfolio.service.ts   # Llama al backend
│   │   └── interceptors/
│   │       └── api.interceptor.ts
│   ├── shared/
│   │   └── components/
│   │       ├── nav/
│   │       └── loader/
│   ├── features/
│   │   ├── inicio/          # Sección Hero
│   │   ├── sobre-mi/        # About
│   │   ├── experiencia/     # Experience (línea de tiempo)
│   │   ├── proyectos/       # Work/Projects (tarjetas)
│   │   └── contacto/        # Contact (formulario)
│   ├── app.component.ts
│   ├── app.routes.ts
│   └── app.config.ts
└── environments/
    ├── environment.ts        # apiUrl: 'http://localhost:8080/api'
    └── environment.prod.ts   # apiUrl: '/api'  (proxy Nginx)
```

---

## Secciones de la SPA (scroll único, sin rutas separadas)
La página es un scroll único con anclas `#inicio`, `#sobre-mi`, `#experiencia`, `#proyectos`, `#contacto`.
El router solo maneja rutas lazy para posibles vistas detalle futuras.

### 1. Navbar
- Logo hexagonal con inicial (esquina superior izquierda)
- Links con numeración: `01. Sobre mí`, `02. Experiencia`, `03. Proyectos`, `04. Contacto`
- Botón `CV` con borde verde menta
- Se oculta/muestra con scroll (clase `scrolled`)
- Barra lateral izquierda: iconos sociales (GitHub, LinkedIn, Twitter)
- Barra lateral derecha: email vertical rotado

### 2. Hero (`#inicio`)
```
// Texto pequeño en fuente mono, color acento
Hola, mi nombre es

// Heading grande, color blanco
[Tu Nombre].

// Heading grande, color text-secondary
Construyo cosas para la web.

// Párrafo descripción
// Botón CTA con borde acento
```
Animación de entrada: fade-in escalonado (cada elemento con delay incremental).

### 3. Sobre mí (`#sobre-mi`)
- Título de sección con numeración: `01. Sobre mí`
- Texto descriptivo a la izquierda
- Foto/avatar a la derecha con overlay verde menta al hover
- Lista de tecnologías con ▹ bullets en color acento

### 4. Experiencia (`#experiencia`)
- Título: `02. Donde he trabajado`
- Tabs verticales por empresa (datos del backend)
- Cada tab: cargo, empresa, fechas, descripción, tecnologías usadas

### 5. Proyectos (`#proyectos`)
- Título: `03. Algunos proyectos`
- Grid de tarjetas con: nombre, descripción, tecnologías, links (GitHub / demo)
- Tarjetas con hover: elevación + borde acento

### 6. Contacto (`#contacto`)
- Título: `04. ¿Qué sigue?`
- Formulario: nombre, email, mensaje
- POST a `/api/contacto`
- Feedback visual de éxito/error

---

## API — Endpoints del backend
```typescript
// GET  /api/sobre-mi         → { nombre, titulo, descripcion, tecnologias[], fotoUrl }
// GET  /api/experiencias      → [ { empresa, cargo, fechaInicio, fechaFin, descripcion, tecnologias[] } ]
// GET  /api/proyectos         → [ { nombre, descripcion, tecnologias[], githubUrl, demoUrl, destacado } ]
// POST /api/contacto          → { nombre, email, mensaje }
```

---

## Comportamientos importantes
- **Scroll spy:** el navbar resalta el link de la sección visible (IntersectionObserver)
- **Loader inicial:** pantalla de carga con logo animado mientras carga la data
- **Responsivo:** mobile-first; el navbar colapsa en menú hamburguesa en móvil
- **Accesibilidad:** aria-labels, focus visible, skip-to-content link
- **CORS:** el backend permite `http://localhost:4200` en desarrollo

---

## Convenciones de código
- Standalone components (sin NgModules salvo AppModule raíz)
- Signals para estado reactivo (`signal()`, `computed()`)
- `inject()` en lugar de constructor DI
- No usar `any` — tipar todo con interfaces en `src/app/core/models/`
- Nombrar archivos: `kebab-case.component.ts`
- Template strings i18n en español directamente en templates (sin ngx-translate por ahora)

---

## Comandos útiles
```bash
ng new portfolio-frontend --standalone --routing --style=scss
ng add @angular/tailwind  # o instalar manualmente
ng g c features/inicio --standalone
ng serve --proxy-config proxy.conf.json   # proxy a localhost:8080
```

### proxy.conf.json
```json
{
  "/api": {
    "target": "http://localhost:8080",
    "secure": false,
    "changeOrigin": true
  }
}
```
