import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { ThemeService } from 'src/app/services/theme.service';
import { Theme } from 'src/app/Model/Entity/theme';
import { Observable } from 'rxjs';
import { SortEvent, SortTableDirective } from 'src/app/Model/Directives/sort-table.directive';
import { Router } from '@angular/router';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.css']
})
export class ThemesComponent implements OnInit {
  themes$: Observable<Theme[]>;
  total$: Observable<number>;
  @ViewChildren(SortTableDirective) headers: QueryList<SortTableDirective>;

  constructor(public themeService: ThemeService, private router: Router) { }

  ngOnInit() {
    this.themes$ = this.themeService.Themes$;
    this.total$ = this.themeService.total$;
  }

  onSort({ column, direction }: SortEvent) {

    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    this.themeService.sortColumn = column;
    this.themeService.sortDirection = direction;
  }

  newTheme(){
    this.router.navigate(['theme/new']);
  }

}
