import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UniteService } from 'src/app/services/unite.service';
import { Unite } from 'src/app/Model/Entity/unite';

@Component({
  selector: 'app-from-unite',
  templateUrl: './from-unite.component.html',
  styleUrls: ['./from-unite.component.css']
})
export class FromUniteComponent implements OnInit {

  formUnite: FormGroup;

  constructor(private form: FormBuilder, private uniteService: UniteService) {
  }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.formUnite = this.form.group({ nom: ['', Validators.required] });
  }

  submit() {
    const data = this.formUnite.value.nom;
    const unite = new Unite();
    unite.value = data;
    this.uniteService.save(unite).subscribe(rep => {
      console.log(rep);
      this.uniteService.getAll();
    });

  }
}
