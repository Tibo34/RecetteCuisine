import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PdfService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = environment.urldatabase + 'pdf';
  }

  createPDF(str: string) {
    const data = { message: str };
    this.http.post(this.url, data).subscribe((rep) => {
      console.log(rep);
    });
  }
}
