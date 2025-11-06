import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { KeynoteRequestDTO, KeynoteResponseDTO } from '../models/keynote.model';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Observable, of } from 'rxjs'; // Import 'of'
import { catchError } from 'rxjs/operators'; // Import 'catchError'

@Component({
  selector: 'app-keynotes',
  templateUrl: './keynotes.html',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  standalone: true,
  styleUrl: "keynotes.css"
})
export class KeynotesComponent implements OnInit {
  // We are using the Observable again
  keynotes$!: Observable<KeynoteResponseDTO[]>;

  form: FormGroup;
  editingId: string | null = null;
  // We keep 'error' to be set by catchError
  error: string | null = null;
  // We REMOVED the 'loading' flag. The async pipe handles this.

  constructor(private api: ApiService, private fb: FormBuilder) {
    this.form = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      fonction: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.loadKeynotes();
  }

  loadKeynotes() {
    this.error = null;
    this.keynotes$ = this.api.getKeynotes().pipe(
      catchError(err => {
        // Set the error and return an empty array
        this.error = err.message || err;
        return of([]); // 'of([])' creates an Observable of an empty array
      })
    );
  }

  submit() {
    if (this.form.invalid) return;
    const dto: KeynoteRequestDTO = this.form.value;

    const action$ = this.editingId
      ? this.api.updateKeynote(this.editingId, dto)
      : this.api.createKeynote(dto);

    action$.subscribe({
      next: () => {
        this.reset();
        this.loadKeynotes(); // Refresh the list
      },
      error: e => this.error = e.message
    });
  }

  edit(k: KeynoteResponseDTO) {
    this.editingId = k.id;
    this.form.patchValue(k);
  }

  deleteOne(id: string) {
    if (!confirm('Delete keynote?')) return;
    this.api.deleteKeynote(id).subscribe({
      // CHANGED: Call loadKeynotes() to refresh
      next: () => this.loadKeynotes(),
      error: e => this.error = e.message
    });
  }

  reset() {
    this.editingId = null;
    this.form.reset();
  }
}
