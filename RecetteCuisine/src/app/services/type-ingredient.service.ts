import { Injectable } from '@angular/core';
import { Ingredient } from '../Model/Entity/ingredient';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TypeIngredient } from '../Model/Entity/type-ingredient';

@Injectable({
  providedIn: 'root'
})
export class TypeIngredientService {
  private ingredients: TypeIngredient[];
  private url: string;

  constructor(private http: HttpClient) {
    this.url = environment.urldatabase + '/TypeIngredients';
  }

  getAll(): Observable<TypeIngredient[]> {
    return this.http.get<TypeIngredient[]>(this.url);
  }
  getById(id: number) {
    return this.http.get<TypeIngredient>(this.url + '/' + id);
  }

  save(ingredient: TypeIngredient) {
    return this.http.post(this.url + '/create', ingredient);
  }
}
