import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TypeIngredientService } from 'src/app/services/type-ingredient.service';
import { TypeIngredient } from 'src/app/Model/Entity/type-ingredient';
import { Unite } from 'src/app/Model/Entity/unite';
import { UniteService } from 'src/app/services/unite.service';
import { Observable } from 'rxjs';
import { FileService } from 'src/app/services/file.service';
import { UploadFileResponse } from 'src/app/Model/Entity/upload-file-response';

@Component({
  selector: 'app-form-type-ingredient',
  templateUrl: './form-type-ingredient.component.html',
  styleUrls: ['./form-type-ingredient.component.css']
})
export class FormTypeIngredientComponent implements OnInit {


  formIngredient: FormGroup;
  uniteSelect: Unite;
  unites: Observable<Unite[]>;
  fileToUpload: File = null;


  constructor(
    private formBuilder: FormBuilder,
    private typeService: TypeIngredientService,
    private uniteService: UniteService,
    private fileService: FileService) {
  }

  ngOnInit() {
    this.initForm();
    this.unites = this.uniteService.unites$;
  }


  initForm() {
    this.formIngredient = this.formBuilder.group({
      nom: ['', Validators.required],
      unite: [Unite]
    });
  }

  uploadImage(files: FileList) {
    this.fileToUpload = files.item(0);
  }


  submit() {
    const data = this.formIngredient.value.nom;
    const ingredient = new TypeIngredient();
    ingredient.nom = data;
    ingredient.unite = this.uniteSelect;
    this.fileService.saveFileImage(this.fileToUpload).subscribe((rep: UploadFileResponse) => {
      ingredient.imageId = rep.id;
      ingredient.image = rep.fileName
      this.typeService.save(ingredient).subscribe(rep => {
        this.typeService.getAll();
      });
    });


  }
}
