import { Component, OnInit } from '@angular/core';
import { TypeIngredientService } from 'src/app/services/type-ingredient.service';
import { ActivatedRoute } from '@angular/router';
import { TypeIngredient } from 'src/app/Model/Entity/type-ingredient';

@Component({
  selector: 'app-typeingredient',
  templateUrl: './typeingredient.component.html',
  styleUrls: ['./typeingredient.component.css']
})
export class TypeingredientComponent implements OnInit {

  load = false;
  typeIngredient: TypeIngredient;

  constructor(private typeService: TypeIngredientService, private router: ActivatedRoute) { }

  ngOnInit() {
    const id = this.router.snapshot.params.id;
    this.typeService.getById(id).subscribe((type: TypeIngredient) => {
      this.typeIngredient = type;
      this.load = true;
    });
  }

}
