import { SortDirection } from '../Directives/sort-table.directive';

export interface State {
  page: number;
  pageSize: number;
  searchTerm: string;
  sortColumn: string;
  sortDirection: SortDirection;
}
