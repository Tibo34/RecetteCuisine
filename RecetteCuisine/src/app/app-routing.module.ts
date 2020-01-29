import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RecettesComponent } from './components/list/recettes/recettes.component';
import { FromRecetteComponent } from './components/from/from-recette/from-recette.component';
import { RecetteComponent } from './components/Detail/recette/recette.component';
import { IngredientsComponent } from './components/list/ingredients/ingredients.component';
import { FormTypeIngredientComponent } from './components/from/form-type-ingredient/form-type-ingredient.component';
import { TypeingredientComponent } from './components/Detail/typeingredient/typeingredient.component';
import { UnitesComponent } from './components/list/unites/unites.component';
import { FromUniteComponent } from './components/from/from-unite/from-unite.component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  {
    path: 'recettes', component: RecettesComponent, children: [
      { path: 'new', component: FromRecetteComponent },
    ]
  },
  {
    path: 'ingredients', component: IngredientsComponent, children: [
      { path: 'new', component: FormTypeIngredientComponent },
      { path: ':id', component: TypeingredientComponent }
    ]
  },
  {
    path: 'unites', component: UnitesComponent, children: [
      { path: 'new', component: FromUniteComponent }
    ]
  },
  { path: 'recette/:id', component: RecetteComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
