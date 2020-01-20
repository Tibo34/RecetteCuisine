package com.example.RecetteCuisine.RecetteCuisine.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RecetteControleur {


    @RequestMapping(value="/Recettes", method= RequestMethod.GET)
    public String getList(){
        return "liste ingrédients";
    }


    @RequestMapping(value = "/Recettes/{id}", method = RequestMethod.GET)
    public String afficherUnProduit(@PathVariable int id) {
        return "Vous avez demandé un ingrédient avec l'id  " + id;
    }
}
