package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Etape;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Ingredient;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Recette;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.EtapeRepository;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.IngredientRepository;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.RecetteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Recettes")
public class RecetteController {
    @Autowired
    private final RecetteRepository recetteRepository;
    @Autowired
    private final EtapeRepository etapeRepository;
    @Autowired
    private final IngredientRepository ingredientRepository;

    private Logger logger = Logger.getLogger(RecetteController.class);

    public RecetteController(RecetteRepository recetteRepository, EtapeRepository etapeRepository, IngredientRepository ingredientRepository) {
        this.recetteRepository = recetteRepository;
        this.etapeRepository = etapeRepository;
        this.ingredientRepository = ingredientRepository;
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Collection<Recette> getList() {
        logger.debug("Get all recettes!");
        Iterable<Recette> recettesIterable = recetteRepository.findAll();
        Collection<Recette> recettes = new ArrayList<>((Collection<? extends Recette>) recettesIterable);
        logger.debug(recettes);
        return recettes;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Recette afficherRecette(@PathVariable int id) {
        logger.debug("recette : " + id);
        Optional<Recette> recette = recetteRepository.findById(id);
        logger.debug(recette);
        return recette.get();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public Recette saveRecette(@RequestBody Recette recette) {
        logger.debug(recette);
        recette = recetteRepository.save(recette);
        return recette;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    public Recette update(@RequestBody Recette recetteUpdate) {
        logger.debug("recette update"+recetteUpdate.getId());
        Recette recette=recetteRepository.findById(recetteUpdate.getId()).get();
        moreEtape(recette,recetteUpdate);
        moreOperation(recette,recetteUpdate);
        recette.update(recetteUpdate);
        recette = recetteRepository.save(recette);
        recette=recetteRepository.findById(recetteUpdate.getId()).get();
        return recette;
    }

    private void moreEtape(Recette recette, Recette recetteUpdate) {
        if(recette.getEtapes().size()<recetteUpdate.getEtapes().size()){
            Etape etape=getNewEtape(recetteUpdate);
            if(etape!=null){
                etapeRepository.save(etape);
            }
        }
    }

    private Etape getNewEtape(Recette recetteUpdate) {
        for (Etape etape: recetteUpdate.getEtapes()) {
            if(Integer.valueOf(etape.getId()) ==null){
                return etape;
            }
        }
        return null;
    }

    private void moreOperation(Recette recette, Recette recetteUpdate){
        if(recette.getIngredients().size()<recetteUpdate.getIngredients().size()){
            Ingredient ingredient=getNewIngredient(recetteUpdate);
            if(ingredient!=null){
                ingredientRepository.save(ingredient);
            }
        }
    }

    private Ingredient getNewIngredient(Recette recetteUpdate) {
        for (Ingredient ingredient: recetteUpdate.getIngredients()) {
            if(Integer.valueOf(ingredient.getId()) ==null){
                return ingredient;
            }
        }
        return null;
    }


}
