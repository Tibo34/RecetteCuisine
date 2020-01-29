import { Ingredient } from './ingredient';

export class Recette {
  id: number;
  nom: string;
  ingredients: Ingredient[];
  content: string;
  shortContent: string;
}
