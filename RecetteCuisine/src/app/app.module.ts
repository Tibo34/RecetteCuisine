import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { FormIngredientComponent } from './components/from/form-ingredient/form-ingredient.component';
import { HeaderComponent } from './components/header/header.component';
import { HttpClientModule } from '@angular/common/http';
import { FromRecetteComponent } from './components/from/from-recette/from-recette.component';
import { FromUniteComponent } from './components/from/from-unite/from-unite.component';
import { FormTypeIngredientComponent } from './components/from/form-type-ingredient/form-type-ingredient.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RecettesComponent } from './components/list/recettes/recettes.component';
import { SortTableDirective } from './Model/Directives/sort-table.directive';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RecetteComponent } from './components/Detail/recette/recette.component';
import { RecettePreviewComponent } from './components/Preview/recette-preview/recette-preview.component';
import { IngredientsComponent } from './components/list/ingredients/ingredients.component';
import { UnitesComponent } from './components/list/unites/unites.component';
import { TypeingredientComponent } from './components/Detail/typeingredient/typeingredient.component';
import { IngredientComponent } from './components/Detail/ingredient/ingredient.component';
import { ThemesComponent } from './components/list/themes/themes.component';
import { FormThemeComponent } from './components/from/form-theme/form-theme.component';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FormIngredientComponent,
    HeaderComponent,
    FromRecetteComponent,
    FromUniteComponent,
    FormTypeIngredientComponent,
    RecettesComponent,
    SortTableDirective,
    RecetteComponent,
    RecettePreviewComponent,
    IngredientsComponent,
    UnitesComponent,
    TypeingredientComponent,
    IngredientComponent,
    ThemesComponent,
    FormThemeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgbModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
