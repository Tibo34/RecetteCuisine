import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { PdfMakeWrapper, Img } from 'pdfmake-wrapper';
import pdfFonts from 'pdfmake/build/vfs_fonts';

@Injectable({
  providedIn: 'root'
})
export class PdfService {


  private pdf = new PdfMakeWrapper();

  constructor() { }

  async createPDF(str: string) {
    PdfMakeWrapper.setFonts(pdfFonts);
    this.pdf.add(str);
    const image = new Img('assets/cuisine.jpg').build();
    this.pdf.add(await image);
    this.pdf.create().download();

  }
}
