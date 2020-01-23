import { Injectable } from '@angular/core';
import { Recette } from '../Model/Entity/recette';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecetteService {

  private recettes: Recette[];
  private url: string;

  constructor(private http: HttpClient) {
    this.url = environment.urldatabase + '/Recettes';
  }

  getAll(): Observable<Recette[]> {
    return this.http.get<Recette[]>(this.url);
  }
  getById(id: number) {
    return this.http.get<Recette>(this.url + '/' + id);
  }

  save(recette: Recette) {
    return this.http.post(this.url + '/create', recette);
  }


}
