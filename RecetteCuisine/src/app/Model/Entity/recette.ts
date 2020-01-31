import { Ingredient } from './ingredient';
import { Etape } from './etape';

export class Recette {
  id: number;
  nom: string;
  ingredients: Ingredient[];
  content: string;
  shortContent: string;
  image: string;
  etapes: Etape[];
  tempCuisson: number;
  tempPreparation: number;
  personneMin:number;
}
