import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, Subject, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TypeIngredient } from '../Model/Entity/type-ingredient';
import { debounceTime, switchMap, tap, delay } from 'rxjs/operators';
import { State } from '../Model/Interfaces/state';
import { SortDirection } from '../Model/Directives/sort-table.directive';


interface SearchResult {
  ingredients: TypeIngredient[];
  total: number;
}


function compare(v1, v2) {
  return v1 < v2 ? -1 : v1 > v2 ? 1 : 0;
}



function sort(recettes: TypeIngredient[], column: string, direction: string): TypeIngredient[] {
  if (direction === '') {
    return recettes;
  } else {
    return [...recettes].sort((a, b) => {
      const res = compare(a[column], b[column]);
      return direction === 'asc' ? res : -res;
    });
  }
}

function matches(typeIngredient: TypeIngredient, term: string) {
  return typeIngredient.nom.toLowerCase().includes(term.toLowerCase());
}

@Injectable({
  providedIn: 'root'
})
export class TypeIngredientService {
  private typeingredients: TypeIngredient[];
  private url: string;
  private _typeIngredients$ = new BehaviorSubject<TypeIngredient[]>([]);
  private _total$ = new BehaviorSubject<number>(0);
  private _loading$ = new BehaviorSubject<boolean>(true);

  private _search$ = new Subject<void>();

  private _state: State = {
    page: 1,
    pageSize: 4,
    searchTerm: '',
    sortColumn: '',
    sortDirection: ''
  };


  constructor(private http: HttpClient) {
    this.url = environment.urldatabase + '/TypeIngredients';
    this.getAll();
    this._search$.pipe(
      tap(() => this._loading$.next(true)),
      debounceTime(200),
      switchMap(() => this._search()),
      delay(200),
      tap(() => this._loading$.next(false))
    ).subscribe(result => {
      this._typeIngredients$.next(result.ingredients);
      this._total$.next(result.total);
    });
    this._search$.next();
  }

  getAll() {
    this.http.get<TypeIngredient[]>(this.url).subscribe((ingredients: TypeIngredient[]) => {
      this.typeingredients = ingredients;
      this._typeIngredients$.next(ingredients);
      this._total$.next(ingredients.length);
    });
  }

  getById(id: number) {
    return this.http.get<TypeIngredient>(this.url + '/' + id);
  }

  save(ingredient: TypeIngredient) {
    return this.http.post(this.url + '/create', ingredient);
  }


  private _set(patch: Partial<State>) {
    Object.assign(this._state, patch);
    this._search$.next();
  }


  get typeIngredients$() {
    return this._typeIngredients$.asObservable();
  }

  get total$() {
    return this._total$.asObservable();
  }

  get loading$() {
    return this._loading$.asObservable();
  }

  get page() {
    return this._state.page;
  }

  get pageSize() {
    return this._state.pageSize;
  }

  get searchTerm() {
    return this._state.searchTerm;
  }

  set page(page: number) {
    this._set({ page });
  }

  set pageSize(pageSize: number) {
    this._set({ pageSize });
  }

  set sortColumn(sortColumn: string) {
    this._set({ sortColumn });
  }

  set sortDirection(sortDirection: SortDirection) {
    this._set({ sortDirection });
  }

  set searchTerm(searchTerm: string) {
    this._set({ searchTerm });
  }


  private _search(): Observable<SearchResult> {
    const { sortColumn, sortDirection, pageSize, page, searchTerm } = this._state;

    // 1. sort
    let typeIngredients: TypeIngredient[] = sort(this.typeingredients, sortColumn, sortDirection);

    // 2. filter
    typeIngredients = typeIngredients.filter(type => matches(type, searchTerm));
    const total = typeIngredients.length;

    // 3. paginate
    typeIngredients = typeIngredients.slice((page - 1) * pageSize, (page - 1) * pageSize + pageSize);
    const search: SearchResult = { ingredients: typeIngredients, total };
    return of(search);
  }
}
