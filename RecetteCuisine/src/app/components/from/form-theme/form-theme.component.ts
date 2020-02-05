import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Theme } from 'src/app/Model/Entity/theme';
import { ThemeService } from 'src/app/services/theme.service';

@Component({
  selector: 'app-form-theme',
  templateUrl: './form-theme.component.html',
  styleUrls: ['./form-theme.component.css']
})
export class FormThemeComponent implements OnInit {


  formTheme: FormGroup;
  constructor(private form: FormBuilder, private themeService: ThemeService) { }



  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.formTheme = this.form.group({ nom: ['', Validators.required] });
  }

  submit() {
    const data = this.formTheme.value.nom;
    const theme = new Theme();
    theme.nom = data;
    this.themeService.save(theme).subscribe(rep => {
      this.themeService.getAll();
    });

  }

}
