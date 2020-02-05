import { Injectable } from '@angular/core';
import { Theme } from '../Model/Entity/theme';
import { BehaviorSubject, Subject, Observable, of } from 'rxjs';
import { State } from '../Model/Interfaces/state';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { tap, debounceTime, switchMap, delay } from 'rxjs/operators';
import { SortDirection } from '../Model/Directives/sort-table.directive';


interface SearchResult {
  themes: Theme[];
  total: number;
}

function compare(v1, v2) {
  return v1 < v2 ? -1 : v1 > v2 ? 1 : 0;
}

function sort(themes: Theme[], column: string, direction: string): Theme[] {
  if (direction === '') {
    return themes;
  } else {
    return [...themes].sort((a, b) => {
      const res = compare(a[column], b[column]);
      return direction === 'asc' ? res : -res;
    });
  }
}

function matches(theme: Theme, term: string, ) {
  return theme.nom.toLowerCase().includes(term.toLowerCase());
}

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  private THEMES: Theme[] = [];
  private _themes$ = new BehaviorSubject<Theme[]>([]);
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
    this.url = environment.urldatabase + '/Themes';
    this.getAll();
    this._search$.pipe(
      tap(() => this._loading$.next(true)),
      debounceTime(200),
      switchMap(() => this._search()),
      delay(200),
      tap(() => this._loading$.next(false))
    ).subscribe(result => {
      this._themes$.next(result.themes);
      this._total$.next(result.total);
    });
    this._search$.next();
  }


  getAll() {
    this.http.get<Theme[]>(this.url).subscribe((themes: Theme[]) => {
      this.THEMES = themes;
      this._themes$.next(this.THEMES);
      this._total$.next(themes.length);
    });
  }




  getById(id: number) {
    return this.http.get<Theme>(this.url + '/' + id);
  }

  save(theme: Theme) {
    return this.http.post(this.url + '/create', theme);
  }

  update(theme: Theme) {
    return this.http.post(this.url + '/update', theme);
  }

  get Themes$() {
    return this._themes$.asObservable();
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

  private _set(patch: Partial<State>) {
    Object.assign(this._state, patch);
    this._search$.next();
  }

  private _search(): Observable<SearchResult> {
    const { sortColumn, sortDirection, pageSize, page, searchTerm } = this._state;

    // 1. sort
    let themes = sort(this.THEMES, sortColumn, sortDirection);

    // 2. filter
    themes = themes.filter(unite => matches(unite, searchTerm));
    const total = themes.length;

    // 3. paginate
    themes = themes.slice((page - 1) * pageSize, (page - 1) * pageSize + pageSize);
    return of({ themes, total });
  }
}
