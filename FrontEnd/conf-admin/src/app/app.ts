import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  standalone: true,
  template: `
  <div class="container mt-3">
    <h1 class="mb-3">Conference Admin</h1>
    <nav>
      <a routerLink="/keynotes" class="me-2">Keynotes</a>
      <a routerLink="/conferences">Conferences</a>
    </nav>
    <hr />
    <router-outlet></router-outlet>
  </div>
  `
})

export class App {
  protected readonly title = signal('conf-admin');
}
