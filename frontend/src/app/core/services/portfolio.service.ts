import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import {
  SobreMi,
  Experiencia,
  Proyecto,
  ContactoRequest,
  ContactoResponse
} from '../models/portfolio.models';

@Injectable({ providedIn: 'root' })
export class PortfolioService {

  private readonly http = inject(HttpClient);
  private readonly base = environment.apiUrl;

  getSobreMi(): Observable<SobreMi> {
    return this.http.get<SobreMi>(`${this.base}/sobre-mi`);
  }

  getExperiencias(): Observable<Experiencia[]> {
    return this.http.get<Experiencia[]>(`${this.base}/experiencias`);
  }

  getProyectos(): Observable<Proyecto[]> {
    return this.http.get<Proyecto[]>(`${this.base}/proyectos`);
  }

  enviarContacto(data: ContactoRequest): Observable<ContactoResponse> {
    return this.http.post<ContactoResponse>(`${this.base}/contacto`, data);
  }
}