import { Component, OnInit } from '@angular/core';
import { Recette } from 'src/app/Model/Entity/recette';
import { RecetteService } from 'src/app/services/recette.service';
import { Observable } from 'rxjs';
import { PdfService } from 'src/app/services/pdf.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  panier: Observable<Recette[]>;
  nb: Observable<number>;
  notEmpty: Observable<boolean>;
  url = environment.urldatabasefileDownload;

  constructor(private recetteService: RecetteService, private pdfService: PdfService) {

  }

  ngOnInit() {
    this.panier = this.recetteService.panierRecette;
    this.nb = this.recetteService.totalPanier;
    this.notEmpty = this.recetteService.notEmpty;
  }

  createPDF() {
    const title = "nouveau livre";
    this.pdfService.createPDFAPI();
  }

}
