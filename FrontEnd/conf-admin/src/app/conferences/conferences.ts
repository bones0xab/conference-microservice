import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import {
  ConferenceRequestDTO, ConferenceResponseDTO
} from '../models/conference.model';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {CommonModule, DatePipe} from '@angular/common';
import { Observable, of } from 'rxjs'; // <-- IMPORTÉ 'of'
import { catchError } from 'rxjs/operators'; // <-- IMPORTÉ 'catchError'

@Component({
  selector: 'app-conferences',
  standalone: true,
  imports: [
    DatePipe,
    ReactiveFormsModule,
    CommonModule,
    FormsModule
  ],
  templateUrl: './conferences.html',
  styleUrl: "conferences.css"
})
export class ConferencesComponent implements OnInit {
  // CHANGÉ : De tableau à Observable
  conferences$!: Observable<ConferenceResponseDTO[]>;

  form: FormGroup;
  editingId: number | null = null;
  types = ['ACADEMIQUE', 'COMMERCIALE'];

  // AJOUTÉ : Propriété pour gérer les erreurs
  error: string | null = null;

  constructor(private api: ApiService, private fb: FormBuilder) {
    this.form = this.fb.group({
      titre: ['', Validators.required],
      type: [this.types[0], Validators.required],
      date: ['', Validators.required],
      duree: [60, [Validators.required, Validators.min(1)]],
      keynoteIds: [''] // comma-separated
    });
  }

  ngOnInit() {
    // CHANGÉ : Appel de la nouvelle méthode
    this.loadConferences();
  }

  // RENOMMÉ ET MODIFIÉ : Configure l'Observable
  loadConferences() {
    this.error = null; // Réinitialise l'erreur
    this.conferences$ = this.api.getConferences().pipe(
      catchError(err => {
        this.error = err.message || 'Failed to load conferences';
        return of([]); // Retourne un tableau vide observable
      })
    );
  }

  submit() {
    if (this.form.invalid) return;
    const raw = this.form.value;
    const dto: ConferenceRequestDTO = {
      titre: raw.titre,
      type: raw.type,
      date: new Date(raw.date).toISOString(),
      duree: Number(raw.duree),
      keynoteIds: raw.keynoteIds ? raw.keynoteIds.split(',').map((s: string) => s.trim()).filter(Boolean) : []
    };

    // Crée l'action observable (Update ou Create)
    const action$ = this.editingId
      ? this.api.updateConference(this.editingId, dto)
      : this.api.createConference(dto);

    // S'abonne à l'action
    action$.subscribe({
      next: () => {
        this.reset();
        this.loadConferences(); // Recharge la liste
      },
      error: e => this.error = e.message
    });
  }

  edit(c: ConferenceResponseDTO) {
    this.editingId = c.id;
    this.form.patchValue({
      titre: c.titre,
      type: c.type,
      // Cette logique est correcte pour 'datetime-local'
      date: new Date(c.date).toISOString().slice(0,16),
      duree: c.duree,
      keynoteIds: c.keynoteIds?.join(', ')
    });
  }

  deleteOne(id: number) {
    if (!confirm('Delete conference?')) return;
    this.api.deleteConference(id).subscribe({
      next: () => this.loadConferences(), // Recharge la liste
      error: e => this.error = e.message
    });
  }

  reset() {
    this.editingId = null;
    this.form.reset({ duree: 60, type: this.types[0], keynoteIds: '' });
  }
}
