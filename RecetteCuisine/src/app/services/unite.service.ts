import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of, Subject } from 'rxjs';
import { debounceTime, delay, switchMap, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { SortDirection } from '../Model/Directives/sort-table.directive';
import { Unite } from '../Model/Entity/unite';
import { State } from '../Model/Interfaces/state';




interface SearchResult {
  unites: Unite[];
  total: number;
}

function compare(v1, v2) {
  return v1 < v2 ? -1 : v1 > v2 ? 1 : 0;
}

function sort(unites: Unite[], column: string, direction: string): Unite[] {
  if (direction === '') {
    return unites;
  } else {
    return [...unites].sort((a, b) => {
      const res = compare(a[column], b[column]);
      return direction === 'asc' ? res : -res;
    });
  }
}

function matches(unite: Unite, term: string, ) {
  return unite.value.toLowerCase().includes(term.toLowerCase());
}


@Injectable({
  providedIn: 'root'
})
export class UniteService {
  private _unites: Unite[] = [];
  private _unites$ = new BehaviorSubject<Unite[]>([]);
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
    this.url = environment.urldatabase + '/Unites';
    this.getAll();
    this.searchUnite();
  }

  private searchUnite() {
    this._search$.pipe(
      tap(() => this._loading$.next(true)),
      debounceTime(200),
      switchMap(() => this._search()),
      delay(200), tap(() => this._loading$.next(false))).subscribe(result => {
        this._unites$.next(result.unites);
        this._total$.next(result.total);
      });
    this._search$.next();
  }

  getAll() {
    this.http.get<Unite[]>(this.url).subscribe((unites: Unite[]) => {
      this._unites = unites;
      this._unites$.next(unites);
      this._total$.next(unites.length);
      this._search$.next();
    });
  }

  getById(id: number) {
    return this.http.get<Unite>(this.url + '/' + id);
  }

  save(unite: Unite) {
    return this.http.post(this.url + '/create', unite);
  }

  get unites$() {
    return this._unites$.asObservable();
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
    let unites = sort(this._unites, sortColumn, sortDirection);

    // 2. filter
    unites = unites.filter(unite => matches(unite, searchTerm));
    const total = unites.length;

    // 3. paginate
    unites = unites.slice((page - 1) * pageSize, (page - 1) * pageSize + pageSize);
    return of({ unites, total });
  }
}
