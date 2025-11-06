import { ReviewResponseDTO } from './review.model';

export enum ConferenceType {
  PUBLIC = 'ACADEMIQUE',
  PRIVATE = 'COMMERCIALE'
}

export interface ConferenceRequestDTO {
  titre: string;
  type: ConferenceType;
  date: string; // send ISO date string
  duree: number;
  keynoteIds?: string[];
}

export interface ConferenceResponseDTO {
  id: number;
  titre: string;
  type: ConferenceType;
  date: string;
  duree: number;
  nbInscrits: number;
  score: number;
  keynoteIds: string[];
  reviews: ReviewResponseDTO[];
}
