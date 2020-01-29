import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { UniteService } from 'src/app/services/unite.service';
import { Router } from '@angular/router';
import { Unite } from 'src/app/Model/Entity/unite';
import { Observable } from 'rxjs';
import { SortTableDirective, SortEvent } from 'src/app/Model/Directives/sort-table.directive';

@Component({
  selector: 'app-unites',
  templateUrl: './unites.component.html',
  styleUrls: ['./unites.component.css']
})
export class UnitesComponent implements OnInit {

  unites$: Observable<Unite[]>;
  total$: Observable<number>;

  @ViewChildren(SortTableDirective) headers: QueryList<SortTableDirective>;

  constructor(public uniteService: UniteService, private router: Router) { }

  ngOnInit() {
    this.unites$ = this.uniteService.unites$;
    this.total$ = this.uniteService.total$;
  }


  onSort({ column, direction }: SortEvent) {

    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    this.uniteService.sortColumn = column;
    this.uniteService.sortDirection = direction;
  }



  newUnite() {
    this.router.navigate(['unites/new']);
  }

}
