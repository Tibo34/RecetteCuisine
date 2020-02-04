import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { PdfMakeWrapper, Img } from 'pdfmake-wrapper';
import pdfFonts from 'pdfmake/build/vfs_fonts';

@Injectable({
  providedIn: 'root'
})
export class PdfService {

  private url: string;
  private pdf = new PdfMakeWrapper();

  constructor(private http: HttpClient) {
    this.url = environment.urldatabase + 'pdf';
  }

  async createPDF(str: string) {
    PdfMakeWrapper.setFonts(pdfFonts);
    const data = { message: str };
    /*this.http.post(this.url, str).subscribe((rep) => {
      console.log(rep);
    });*/
    this.pdf.add(str);
    const image=new Img('assets/cuisine.jpg').build();
    this.pdf.add(await image);
    this.pdf.create().download();

  }
}
