import { Component, OnInit } from '@angular/core';
import { Recette } from 'src/app/Model/Entity/recette';
import { RecetteService } from 'src/app/services/recette.service';
import { Route, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-recette',
  templateUrl: './recette.component.html',
  styleUrls: ['./recette.component.css']
})
export class RecetteComponent implements OnInit {

  recette: Recette;
  load=false;

  constructor(private recetteService: RecetteService, private router: ActivatedRoute) { }

  ngOnInit() {
    const id = this.router.snapshot.params.id;
    console.log(id);
    this.recetteService.getById(id).subscribe((recette: Recette) => {
      this.recette = recette;
      console.log(recette);
      this.load=true;
    });
  }

}
