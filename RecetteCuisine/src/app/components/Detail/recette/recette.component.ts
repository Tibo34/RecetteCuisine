import { Component, OnInit } from '@angular/core';
import { Recette } from 'src/app/Model/Entity/recette';
import { RecetteService } from 'src/app/services/recette.service';
import { Route, ActivatedRoute } from '@angular/router';
import { TypeIngredientService } from 'src/app/services/type-ingredient.service';
import { TypeIngredient } from 'src/app/Model/Entity/type-ingredient';
import { Observable } from 'rxjs';
import { Ingredient } from 'src/app/Model/Entity/ingredient';
import { Etape } from 'src/app/Model/Entity/etape';
import { IngredientsService } from 'src/app/services/ingredients.service';
import { EtapeService } from 'src/app/services/etape.service';

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
  personnes: number;
  content: string;

  edit = true;
  plus = false;
  addEtape = false;

  constructor(
    private recetteService: RecetteService,
    private router: ActivatedRoute,
    private typeIngredientService: TypeIngredientService,
    private ingredientService: IngredientsService,
    private etapeService: EtapeService) { }

  ngOnInit() {
    const id = this.router.snapshot.params.id;
    this.recetteService.getById(id).subscribe((recette: Recette) => {
      this.recette = recette;
      this.load = true;
      this.personnes = recette.personneMin;
    });
    this.typeIngredient$ = this.typeIngredientService.typeIngredients$;
  }

  valideRecette() {
    console.log(this.recette);
    this.recetteService.update(this.recette).subscribe((rep: Recette) => {
      this.recette = rep;
    });
  }

  addIngredient() {
    const ingredient = new Ingredient();
    ingredient.quantite = this.quantite;
    ingredient.type = this.typeIngredient;
    ingredient.recetteId = this.recette.id;
    console.log(ingredient);
    this.ingredientService.save(ingredient).subscribe((newIngredient: Ingredient) => {
      this.recette.ingredients.push(newIngredient);
    });


    //this.valideRecette();
  }

  downPersonne() {
    --this.personnes;
  }

  upPersonne() {
    ++this.personnes;
  }

  newEtape() {
    const etape = new Etape();
    etape.content = this.content;
    etape.recette_id = this.recette.id;
    etape.rang = this.getlastRang();
    this.etapeService.save(etape).subscribe((newEtape: Etape) => {
      this.recette.etapes.push(newEtape);
    });

    console.log(etape);
    // this.valideRecette();
  }

  getlastRang() {
    let max = 0;
    this.recette.etapes.forEach(etape => {
      if (etape.rang > max) {
        max = etape.rang;
      }
    });
    return ++max;
  }
}
