import { Component, OnInit } from '@angular/core';
import { PdfService } from 'src/app/services/pdf.service';
import { FileService } from 'src/app/services/file.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  pdf = false;
  pdfText: string;

  constructor(private pdfService: PdfService, private fileService: FileService) {
  }

  ngOnInit() {
  }


  pdfCreate() {
    this.pdfService.createPDF(this.pdfText);
  }

  uploadImage(file: File) {
    console.log(file);
    this.fileService.saveFileImage(file[0]).subscribe((rep) => {
      console.log(rep);
    });
  }
}
