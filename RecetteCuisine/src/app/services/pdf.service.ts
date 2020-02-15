import { Injectable, Sanitizer, SecurityContext } from '@angular/core';
import { PdfMakeWrapper, Img, Txt } from 'pdfmake-wrapper';
import pdfFonts from 'pdfmake/build/vfs_fonts';
import { RecetteService } from './recette.service';
import { Observable } from 'rxjs';
import { Recette } from '../Model/Entity/recette';
import { IImg } from 'pdfmake-wrapper/lib/interfaces';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FilePDF } from '../Model/Entity/file-pdf';
import { DomSanitizer } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})
export class PdfService {


  private pdf = new PdfMakeWrapper();
  recettes$: Observable<Recette[]>;
  recettes: Recette[] = [];
  promises: Promise<IImg>[] = [];
  url = environment.urldatabase + 'file/pdf';


  constructor(private recetteService: RecetteService, private http: HttpClient, private sanitazer: DomSanitizer) {

    this.recettes$ = this.recetteService.panierRecette;
    this.recetteService.panierRecette.subscribe((recettes: Recette[]) => {
      this.recettes = recettes;
    });
  }

  public createPDFAPI() {
    const recettesId = [];
    this.recettes.forEach(r => {
      recettesId.push(r.id);
    });
    const httpOptions = {
      responseType: 'arraybuffer' as 'json'
    };


    this.http.post(this.url, recettesId, httpOptions).subscribe((rep) => {
      this.downLoadFile(rep);
    }, error => {
      console.error(error);
    });
  }


  /**
   * Method is use to download file.
   * @param data - Array Buffer data
   * @param type - type of the document.
   */
  downLoadFile(data: any) {
    const blob = new Blob([data], { type: 'application/pdf' });
    const url = URL.createObjectURL(blob);
    const pwa = window.open(url);
    if (!pwa || pwa.closed || typeof pwa.closed === 'undefined') {
      alert('Please disable your Pop-up blocker and try again.');
    }
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
        this.pdf.add(await imgIngredient);
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
