import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-from-recette',
  templateUrl: './from-recette.component.html',
  styleUrls: ['./from-recette.component.css']
})
export class FromRecetteComponent implements OnInit {

  formRecette: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
  }

  initForm() {
    this.formRecette = this.formBuilder.group({
      nom: ['', Validators.required]
    });
  }

}
