import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {RecettesComponent} from './components/list/recettes/recettes.component';
import {FromRecetteComponent} from './components/from/from-recette/from-recette.component';


const routes: Routes = [
  {path: '', component: HomeComponent},
  {
    path: 'recettes', component: RecettesComponent, children: [
      {path: 'newRecette', component: FromRecetteComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
