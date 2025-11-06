export interface ReviewResponseDTO {
  id: number;
  date: string; // backend sends Date -> string iso
  texte: string;
  note: number;
}
