import { Component, OnInit, Input } from '@angular/core';
import { Ingredient } from 'src/app/Model/Entity/ingredient';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { FileService } from 'src/app/services/file.service';

@Component({
  selector: 'app-ingredient',
  templateUrl: './ingredient.component.html',
  styleUrls: ['./ingredient.component.css']
})
export class IngredientComponent implements OnInit {

  @Input()
  ingredient: Ingredient;
  @Input()
  personnes: number;
  @Input()
  personneMin: number;
  quantite: number;
  urlImageIngredient: string;
  isImageLoading: boolean;

  imageToShow: any;

  constructor(private fileService: FileService) { }

  ngOnInit() {
    this.quantite = (this.ingredient.quantite / this.personneMin) * this.personnes;
    this.urlImageIngredient = 'file/ingredient/' + this.ingredient.type.image;
  }
  createImageFromBlob(image: Blob) {
    const reader = new FileReader();
    reader.addEventListener('load', () => {
      this.imageToShow = reader.result;
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }

  getImageFromService() {
    this.isImageLoading = true;
    this.fileService.loadImage(this.urlImageIngredient).subscribe((data: Blob) => {
      this.createImageFromBlob(data);
      this.isImageLoading = false;
    }, error => {
      this.isImageLoading = false;
      console.log(error);
    });
  }
}
