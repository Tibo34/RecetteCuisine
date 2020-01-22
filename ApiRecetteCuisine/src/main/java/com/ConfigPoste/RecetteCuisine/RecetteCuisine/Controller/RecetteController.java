package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Recette;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.RecetteRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecetteController {

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String home(){
        return "Home recettes cuisines";
    }

    @RequestMapping(value="/Recettes", method= RequestMethod.GET,produces = "application/json")
    public List<Recette> getList(){
        List<Recette> recettes=new RecetteRepository().getAll();
        return recettes;
    }


    @RequestMapping(value = "/Recettes/{id}", method = RequestMethod.GET,produces = "application/json")
    public String afficherUnProduit(@PathVariable int id) {
        return "Vous avez demandé un ingrédient avec l'id  " + id;
    }
}
