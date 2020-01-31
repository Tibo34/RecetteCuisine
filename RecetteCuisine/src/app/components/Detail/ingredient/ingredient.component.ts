import { Component, OnInit, Input } from '@angular/core';
import { Ingredient } from 'src/app/Model/Entity/ingredient';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-ingredient',
  templateUrl: './ingredient.component.html',
  styleUrls: ['./ingredient.component.css']
})
export class IngredientComponent implements OnInit {

  @Input()
  ingredient: Ingredient;
  @Input()
  personnes: number;
  @Input()
  personneMin: number;
  quantite: number;

  constructor() { }

  ngOnInit() {
    this.quantite = (this.ingredient.quantite / this.personneMin) * this.personnes;
  }


}
