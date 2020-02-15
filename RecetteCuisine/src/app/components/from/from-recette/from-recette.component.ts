import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Recette } from 'src/app/Model/Entity/recette';
import { RecetteService } from 'src/app/services/recette.service';
import { FileService } from 'src/app/services/file.service';
import { UploadFileResponse } from 'src/app/Model/Entity/upload-file-response';

@Component({
  selector: 'app-from-recette',
  templateUrl: './from-recette.component.html',
  styleUrls: ['./from-recette.component.css']
})
export class FromRecetteComponent implements OnInit {

  formRecette: FormGroup;
  fileToUpload: File = null;

  constructor(private formBuilder: FormBuilder, private recetteService: RecetteService, private fileService: FileService) {
  }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.formRecette = this.formBuilder.group({
      nom: ['', Validators.required],
      content: ['', Validators.required]
    });
  }

  uploadImage(files: FileList) {
    this.fileToUpload = files.item(0);
  }

  submit() {
    const data = this.formRecette.value;
    const recette = new Recette();
    recette.nom = data.nom;
    recette.shortContent = data.content;

    this.fileService.saveFileImage(this.fileToUpload).subscribe((rep: UploadFileResponse) => {
      recette.imageId = rep.id;
      recette.image = rep.fileName;
      this.recetteService.save(recette).subscribe(rep => {
        this.recetteService.getAll();
      });
    });
  }

}
