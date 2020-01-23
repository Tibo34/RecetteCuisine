import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { Recette } from 'src/app/Model/Entity/recette';
import { RecetteService } from 'src/app/services/recette.service';
import { SortEvent, SortTableDirective } from 'src/app/Model/Directives/sort-table.directive';

@Component({
  selector: 'app-recettes',
  templateUrl: './recettes.component.html',
  styleUrls: ['./recettes.component.css']
})
export class RecettesComponent implements OnInit {

  private listrecettes: Recette[] = [];
  load = false;
  @ViewChildren(SortTableDirective) headers: QueryList<SortTableDirective>;
  sortDirection: string;
  sortColumn: string;
  page = 1;
  pageSize = 4;
  collectionSize: number;


  constructor(private recettesSerice: RecetteService) { }

  ngOnInit() {
    this.recettesSerice.getAll().subscribe((rep) => {
      this.listrecettes = rep;
      this.load = true;
      this.collectionSize = this.listrecettes.length;
    });
  }

  get recettes(): Recette[] {
    return this.listrecettes
      .map((recette, i) => ({ id: i + 1, ...recette }))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    this.sortColumn = column;
    this.sortDirection = direction;
  }

}
