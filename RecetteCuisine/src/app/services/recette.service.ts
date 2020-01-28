import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, of, Subject} from 'rxjs';
import {debounceTime, delay, switchMap, tap} from 'rxjs/operators';
import {environment} from 'src/environments/environment';
import {SortDirection} from '../Model/Directives/sort-table.directive';
import {Recette} from '../Model/Entity/recette';


interface State {
  page: number;
  pageSize: number;
  searchTerm: string;
  sortColumn: string;
  sortDirection: SortDirection;
}

interface SearchResult {
  recettes: Recette[];
  total: number;
}

function compare(v1, v2) {
  return v1 < v2 ? -1 : v1 > v2 ? 1 : 0;
}

function sort(recettes: Recette[], column: string, direction: string): Recette[] {
  if (direction === '') {
    return recettes;
  } else {
    return [...recettes].sort((a, b) => {
      const res = compare(a[column], b[column]);
      return direction === 'asc' ? res : -res;
    });
  }
}

function matches(recette: Recette, term: string,) {
  return recette.nom.toLowerCase().includes(term.toLowerCase());
}


@Injectable({
  providedIn: 'root'
})
export class RecetteService {

  private RECETTES: Recette[] = [];
  private _recettes$ = new BehaviorSubject<Recette[]>([]);
  private _total$ = new BehaviorSubject<number>(0);
  private _loading$ = new BehaviorSubject<boolean>(true);
  private url: string;
  private _search$ = new Subject<void>();


  private _state: State = {
    page: 1,
    pageSize: 4,
    searchTerm: '',
    sortColumn: '',
    sortDirection: ''
  };

  constructor(private http: HttpClient) {
    this.url = environment.urldatabase + '/Recettes';
    this.getAll();
    this._search$.pipe(
      tap(() => this._loading$.next(true)),
      debounceTime(200),
      switchMap(() => this._search()),
      delay(200),
      tap(() => this._loading$.next(false))
    ).subscribe(result => {
      this._recettes$.next(result.recettes);
      this._total$.next(result.total);
    });
    this._search$.next();
  }

  getAll() {
    this.http.get<Recette[]>(this.url).subscribe((recettes: Recette[]) => {
      this.RECETTES = recettes;
      this._recettes$.next(recettes);
      this._total$.next(recettes.length);
    });
  }


  getById(id: number) {
    return this.http.get<Recette>(this.url + '/' + id);
  }

  save(recette: Recette) {
    return this.http.post(this.url + '/create', recette);
  }

  get recettes$() {
    return this._recettes$.asObservable();
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
    this._set({page});
  }

  set pageSize(pageSize: number) {
    this._set({pageSize});
  }

  set sortColumn(sortColumn: string) {
    this._set({sortColumn});
  }

  set sortDirection(sortDirection: SortDirection) {
    this._set({sortDirection});
  }

  set searchTerm(searchTerm: string) {
    this._set({searchTerm});
  }

  private _set(patch: Partial<State>) {
    Object.assign(this._state, patch);
    this._search$.next();
  }

  private _search(): Observable<SearchResult> {
    const {sortColumn, sortDirection, pageSize, page, searchTerm} = this._state;

    // 1. sort
    let recettes = sort(this.RECETTES, sortColumn, sortDirection);

    // 2. filter
    recettes = recettes.filter(country => matches(country, searchTerm));
    const total = recettes.length;

    // 3. paginate
    recettes = recettes.slice((page - 1) * pageSize, (page - 1) * pageSize + pageSize);
    return of({recettes, total});
  }

}
