import { Injectable } from '@angular/core';
import { Ingredient } from '../Model/Entity/ingredient';
import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IngredientsService {

  private _ingredients: Ingredient[];
  private url: string;


  constructor(private http: HttpClient) {
    this.url = environment.urldatabase + 'Ingredients';
    this.getAll();
  }

  getAll() {
    this.http.get<Ingredient[]>(this.url).subscribe((ingredients: Ingredient[]) => {
      this._ingredients = ingredients;
    });
  }

  getById(id: number) {
    return this.http.get<Ingredient>(this.url + '/' + id);
  }

  save(ingredient: Ingredient) {
    return this.http.post(this.url + '/create', ingredient);
  }

  delete(ingredient: Ingredient) {
    return this.http.get(this.url + '/delete/' + ingredient.id);
  }
}
