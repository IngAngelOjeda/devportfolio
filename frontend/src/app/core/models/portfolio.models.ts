export interface SobreMi {
  nombre: string;
  titulo: string;
  descripcion: string;
  tecnologias: string[];
  fotoUrl: string;
  githubUrl: string;
  linkedinUrl: string;
  email: string;
}

export interface Experiencia {
  id: number;
  empresa: string;
  cargo: string;
  fechaInicio: string;
  fechaFin: string;
  descripcion: string;
  tecnologias: string[];
}

export interface Proyecto {
  id: number;
  nombre: string;
  descripcion: string;
  tecnologias: string[];
  githubUrl: string;
  demoUrl: string | null;
  destacado: boolean;
}

export interface ContactoRequest {
  nombre: string;
  email: string;
  mensaje: string;
}

export interface ContactoResponse {
  mensaje: string;
}