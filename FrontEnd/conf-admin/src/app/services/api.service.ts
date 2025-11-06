import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  KeynoteRequestDTO, KeynoteResponseDTO
} from '../models/keynote.model';
import {
  ConferenceRequestDTO, ConferenceResponseDTO
} from '../models/conference.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private base = '/api'; // proxied to http://localhost:9999

  constructor(private http: HttpClient) {}

  // Keynotes
  getKeynotes(): Observable<KeynoteResponseDTO[]> {
    return this.http.get<KeynoteResponseDTO[]>(`${this.base}/keynotes`);
  }
  getKeynote(id: string) {
    return this.http.get<KeynoteResponseDTO>(`${this.base}/keynotes/${id}`);
  }
  createKeynote(dto: KeynoteRequestDTO) {
    return this.http.post<KeynoteResponseDTO>(`${this.base}/keynotes`, dto);
  }
  updateKeynote(id: string, dto: KeynoteRequestDTO) {
    return this.http.put<KeynoteResponseDTO>(`${this.base}/keynotes/${id}`, dto);
  }
  deleteKeynote(id: string) {
    return this.http.delete<void>(`${this.base}/keynotes/${id}`);
  }

  // Conferences
  getConferences(): Observable<ConferenceResponseDTO[]> {
    return this.http.get<ConferenceResponseDTO[]>(`${this.base}/conferences`);
  }
  getConference(id: number) {
    return this.http.get<ConferenceResponseDTO>(`${this.base}/conferences/${id}`);
  }
  createConference(dto: ConferenceRequestDTO) {
    return this.http.post<ConferenceResponseDTO>(`${this.base}/conferences`, dto);
  }
  updateConference(id: number, dto: ConferenceRequestDTO) {
    return this.http.put<ConferenceResponseDTO>(`${this.base}/conferences/${id}`, dto);
  }
  deleteConference(id: number) {
    return this.http.delete<void>(`${this.base}/conferences/${id}`);
  }
}
