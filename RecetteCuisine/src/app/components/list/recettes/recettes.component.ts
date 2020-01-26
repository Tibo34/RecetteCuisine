import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { Recette } from 'src/app/Model/Entity/recette';
import { RecetteService } from 'src/app/services/recette.service';
import { SortEvent, SortTableDirective } from 'src/app/Model/Directives/sort-table.directive';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-recettes',
  templateUrl: './recettes.component.html',
  styleUrls: ['./recettes.component.css']
})
export class RecettesComponent implements OnInit {

  recettes$:Observable<Recette[]>;
  total$: Observable<number>;

  @ViewChildren(SortTableDirective) headers: QueryList<SortTableDirective>;



  constructor(public recettesService: RecetteService) { }

  ngOnInit() {
    this.recettes$=this.recettesService.recettes$;
    this.total$ = this.recettesService.total$;
  }


  onSort({ column, direction }: SortEvent) {

    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    this.recettesService.sortColumn = column;
    this.recettesService.sortDirection = direction;


  }

  }
