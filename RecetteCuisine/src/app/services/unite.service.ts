import {Injectable} from '@angular/core';
import {Unite} from '../Model/Entity/unite';
import {HttpClient} from '@angular/common/http';
import {environment} from 'src/environments/environment';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UniteService {
  private unites: Unite[];
  private url: string;

  constructor(private http: HttpClient) {
    this.url = environment.urldatabase + '/Unites';
  }

  getAll(): Observable<Unite[]> {
    return this.http.get<Unite[]>(this.url);
  }

  getById(id: number) {
    return this.http.get<Unite>(this.url + '/' + id);
  }

  save(unite: Unite) {
    return this.http.post(this.url + '/create', unite);
  }
}
