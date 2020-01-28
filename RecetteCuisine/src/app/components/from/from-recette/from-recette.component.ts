import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import {Recette} from 'src/app/Model/Entity/recette';
import {RecetteService} from 'src/app/services/recette.service';

@Component({
  selector: 'app-from-recette',
  templateUrl: './from-recette.component.html',
  styleUrls: ['./from-recette.component.css']
})
export class FromRecetteComponent implements OnInit {

  formRecette: FormGroup;

  constructor(private formBuilder: FormBuilder, private recetteService: RecetteService) {
  }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.formRecette = this.formBuilder.group({
      nom: ['', Validators.required]
    });
  }

  submit() {
    const data = this.formRecette.value.nom;
    const recette = new Recette();
    recette.nom = data;
    this.recetteService.save(recette).subscribe(rep => {
      console.log(rep);
    });
  }

}
