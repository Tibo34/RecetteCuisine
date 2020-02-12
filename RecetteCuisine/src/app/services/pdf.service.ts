import { Injectable } from '@angular/core';
import { PdfMakeWrapper, Img, Txt } from 'pdfmake-wrapper';
import pdfFonts from 'pdfmake/build/vfs_fonts';
import { RecetteService } from './recette.service';
import { Observable } from 'rxjs';
import { Recette } from '../Model/Entity/recette';
import { IImg } from 'pdfmake-wrapper/lib/interfaces';

@Injectable({
  providedIn: 'root'
})
export class PdfService {


  private pdf = new PdfMakeWrapper();
  recettes$: Observable<Recette[]>;
  recettes: Recette[] = [];
  promises: Promise<IImg>[] = [];


  constructor(private recetteService: RecetteService) {
    this.recettes$ = this.recetteService.panierRecette;
    this.recetteService.panierRecette.subscribe((recettes: Recette[]) => {
      this.recettes = recettes;
      console.log(this.recettes);
    });
  }

  async createPDF(str: string) {
    PdfMakeWrapper.setFonts(pdfFonts);
    this.pdf.info({
      title: 'Livre de recette',
      author: 'Thibaut',
      subject: 'recette de cuisine',
    });
    const imagesUrl = 'assets/images/';
    const image = new Img('assets/cuisine.jpg').build();
    this.promises.push(image);
    this.pdf.pageSize('A4');
    this.pdf.header(str);
    this.pdf.add(await image);

    this.recettes.forEach(async recette => {

      const img = new Img(imagesUrl + 'recettes/' + recette.image).build();
      this.promises.push(img);
      this.pdf.add(await img);
      this.pdf.add(recette.nom);

      this.pdf.add('nombre de personne : ' + recette.personneMin);
      this.pdf.add('cuisson : ' + recette.tempCuisson + ' préparation :' + recette.tempPreparation);

      recette.ingredients.forEach(async ingredient => {
        const imgIngredient = new Img(imagesUrl + 'ingredients/' + ingredient.type.image).build();
        this.promises.push(imgIngredient);
        this.pdf.add(await imgIngredient );
        this.pdf.add(ingredient.type.nom + ' ' + ingredient.quantite + ' ' + ingredient.type.unite.value);
      });
      recette.etapes.forEach(etape => {
        this.pdf.add(etape.rang + ' ' + etape.content);
      });
    });
    Promise.all(this.promises).then(() => {
      this.pdf.create().download('recette.pdf');
    });
  }




}
