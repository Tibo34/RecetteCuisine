import { Component, OnInit } from '@angular/core';
import { Recette } from 'src/app/Model/Entity/recette';
import { RecetteService } from 'src/app/services/recette.service';
import { Route, ActivatedRoute } from '@angular/router';
import { TypeIngredientService } from 'src/app/services/type-ingredient.service';
import { TypeIngredient } from 'src/app/Model/Entity/type-ingredient';
import { Observable } from 'rxjs';
import { Ingredient } from 'src/app/Model/Entity/ingredient';

@Component({
  selector: 'app-recette',
  templateUrl: './recette.component.html',
  styleUrls: ['./recette.component.css']
})
export class RecetteComponent implements OnInit {

  recette: Recette;
  load = false;
  typeIngredient: TypeIngredient;
  typeIngredient$: Observable<TypeIngredient[]>;
  quantite: number;

  edit = true;
  plus = false;
  constructor(private recetteService: RecetteService,
    private router: ActivatedRoute, private typeIngredientService: TypeIngredientService) { }

  ngOnInit() {
    const id = this.router.snapshot.params.id;
    this.recetteService.getById(id).subscribe((recette: Recette) => {
      this.recette = recette;
      this.load = true;
    });
    this.typeIngredient$ = this.typeIngredientService.typeIngredients$;
  }

  valideRecette() {
    this.recetteService.update(this.recette).subscribe((rep: Recette) => {
      this.recette = rep;
    });
  }

  addIngredient() {
    const ingredient = new Ingredient();
    ingredient.quantite = this.quantite;
    ingredient.type = this.typeIngredient;
    this.recette.ingredients.push(ingredient);
    this.valideRecette();
  }


}
