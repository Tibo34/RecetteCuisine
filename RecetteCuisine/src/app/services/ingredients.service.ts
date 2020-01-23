import { Injectable } from '@angular/core';
import { Ingredient } from '../Model/Entity/ingredient';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IngredientsService {

  private ingredients: Ingredient[];
  private url: string;

  constructor(private http: HttpClient) {
    this.url = environment.urldatabase + '/Ingredients';
  }

  getAll(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(this.url);
  }
  getById(id: number) {
    return this.http.get<Ingredient>(this.url + '/' + id);
  }

  save(ingredient: Ingredient) {
    return this.http.post(this.url + '/create', ingredient);
  }
}
