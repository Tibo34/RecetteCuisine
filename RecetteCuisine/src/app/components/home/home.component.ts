import { Component, OnInit } from '@angular/core';
import { PdfService } from 'src/app/services/pdf.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  pdf = false;
  pdfText: string;

  constructor(private pdfService:PdfService) {
  }

  ngOnInit() {
  }


  pdfCreate() {
    this.pdfService.createPDF(this.pdfText);
  }
}
