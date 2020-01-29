import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { TypeIngredientService } from 'src/app/services/type-ingredient.service';
import { TypeIngredient } from 'src/app/Model/Entity/type-ingredient';
import { Observable } from 'rxjs';
import { SortTableDirective, SortEvent } from 'src/app/Model/Directives/sort-table.directive';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ingredients',
  templateUrl: './ingredients.component.html',
  styleUrls: ['./ingredients.component.css']
})
export class IngredientsComponent implements OnInit {

  ingredients$: Observable<TypeIngredient[]>;
  total$: Observable<number>;

  @ViewChildren(SortTableDirective) headers: QueryList<SortTableDirective>;


  constructor(public typeIngredientsService: TypeIngredientService,private router:Router) { }

  ngOnInit() {
    this.ingredients$ = this.typeIngredientsService.typeIngredients$;
    this.total$ = this.typeIngredientsService.total$;
  }

  onSort({ column, direction }: SortEvent) {

    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    this.typeIngredientsService.sortColumn = column;
    this.typeIngredientsService.sortDirection = direction;
  }

  newIngredient(){
    this.router.navigate(['ingredients/new']);
  }

  Detail(id:number){
    this.router.navigate(['ingedient/' + id]);
  }
}
