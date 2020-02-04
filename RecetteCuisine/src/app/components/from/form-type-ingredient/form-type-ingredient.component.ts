import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TypeIngredientService } from 'src/app/services/type-ingredient.service';
import { TypeIngredient } from 'src/app/Model/Entity/type-ingredient';
import { Unite } from 'src/app/Model/Entity/unite';
import { UniteService } from 'src/app/services/unite.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-form-type-ingredient',
  templateUrl: './form-type-ingredient.component.html',
  styleUrls: ['./form-type-ingredient.component.css']
})
export class FormTypeIngredientComponent implements OnInit {


  formIngredient: FormGroup;
  uniteSelect: Unite;
  unites: Observable<Unite[]>;

  constructor(private formBuilder: FormBuilder, private typeService: TypeIngredientService, private uniteService: UniteService) {
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


  submit() {
    const data = this.formIngredient.value.nom;
    const ingredient = new TypeIngredient();
    ingredient.nom = data;
    ingredient.unite = this.uniteSelect;
    this.typeService.save(ingredient).subscribe(rep => {
      console.log(rep);
      this.typeService.getAll();
    });

  }
}
