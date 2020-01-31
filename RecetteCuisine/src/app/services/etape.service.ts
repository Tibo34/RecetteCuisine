import { Injectable } from '@angular/core';
import { Etape } from '../Model/Entity/etape';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EtapeService {
  private _etapes: Etape[];
  private url: string;


  constructor(private http: HttpClient) {
    this.url = environment.urldatabase + '/Etapes';
    this.getAll();
  }

  getAll() {
    this.http.get<Etape[]>(this.url).subscribe((ingredients: Etape[]) => {
      this._etapes = ingredients;
    });
  }

  getById(id: number) {
    return this.http.get<Etape>(this.url + '/' + id);
  }

  save(etape: Etape) {
    return this.http.post(this.url + '/create', etape);
  }
}
