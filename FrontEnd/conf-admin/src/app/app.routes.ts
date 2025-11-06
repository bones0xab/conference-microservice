import { Routes } from '@angular/router';
import {KeynotesComponent} from './keynotes/keynotes';
import {ConferencesComponent} from './conferences/conferences';
// import {authGuard} from './auth.guard';
import {createAuthGuard} from "keycloak-angular"
import {authGuard} from './auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'keynotes', pathMatch: 'full' },
  { path: 'keynotes', component: KeynotesComponent ,canActivate:[authGuard] },
  { path: 'conferences', component: ConferencesComponent, canActivate: [authGuard] }
];
