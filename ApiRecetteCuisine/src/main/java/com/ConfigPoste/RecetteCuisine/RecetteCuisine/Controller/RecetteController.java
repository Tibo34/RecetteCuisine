package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Recette;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Recettes")
public class RecetteController {


    private final RecetteRepository recetteRepository;

    @Autowired
    RecetteController() {
        this.recetteRepository = new RecetteRepository();
    }



    @RequestMapping(value="", method= RequestMethod.GET,produces = "application/json")
    public List<Recette> getList(){
        List<Recette> recettes=recetteRepository.getAll();
        return recettes;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = "application/json")
    public Recette afficherRecette(@PathVariable int id) {
        Recette recette=recetteRepository.getById(1);
        return recette;
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET.POST,produces = "application/json")
    public Recette saveRecette(@RequestBody Recette recette){
        recette=recetteRepository.save(recette);
        return recette;
    }
}
