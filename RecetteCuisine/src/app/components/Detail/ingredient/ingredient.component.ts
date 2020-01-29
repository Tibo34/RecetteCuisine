import { Component, OnInit, Input } from '@angular/core';
import { Ingredient } from 'src/app/Model/Entity/ingredient';

@Component({
  selector: 'app-ingredient',
  templateUrl: './ingredient.component.html',
  styleUrls: ['./ingredient.component.css']
})
export class IngredientComponent implements OnInit {

  @Input()
  ingredient:Ingredient;

  constructor() { }

  ngOnInit() {
  }

}
