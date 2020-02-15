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
import { ThemeService } from 'src/app/services/theme.service';
import { Theme } from 'src/app/Model/Entity/theme';
import { environment } from 'src/environments/environment';

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
  themes$: Observable<Theme[]>;
  quantite: number;
  personnes: number;
  content: string;
  themeSelect: Theme;
  tempCuisson: string;
  tempPreparation: string;
  edit = true;
  plus = false;
  addEtape = false;
  editTemp = false;
  url = environment.urldatabasefileDownload;

  constructor(
    private recetteService: RecetteService,
    private router: ActivatedRoute,
    private typeIngredientService: TypeIngredientService,
    private ingredientService: IngredientsService,
    private etapeService: EtapeService,
    private themeService: ThemeService) { }

  ngOnInit() {
    const id = this.router.snapshot.params.id;
    this.recetteService.getById(id).subscribe((recette: Recette) => {
      this.recette = recette;
      this.load = true;
      this.personnes = recette.personneMin;
    });
    this.typeIngredient$ = this.typeIngredientService.typeIngredients$;
    this.themes$ = this.themeService.Themes$;
  }

  editTempPrepCuisson() {
    this.editTemp = !this.editTemp;
  }

  valideRecette() {
    console.log(this.recette);
    this.recetteService.update(this.recette).subscribe((rep: Recette) => {
      this.recette = rep;
    });
  }

  updateTemp() {
    this.recette.tempCuisson = parseInt(this.tempCuisson, 10);
    this.recette.tempPreparation = parseInt(this.tempPreparation, 10);
    this.valideRecette();
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
  }

  deleteIngredient(ingredient: Ingredient) {
    this.ingredientService.delete(ingredient).subscribe(() => {
      this.recette.ingredients = this.recette.ingredients.filter(i => i.id !== ingredient.id);
    });
  }

  addTheme() {
    console.log(this.themeSelect);
    this.recette.theme = this.themeSelect;
    this.valideRecette();
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
