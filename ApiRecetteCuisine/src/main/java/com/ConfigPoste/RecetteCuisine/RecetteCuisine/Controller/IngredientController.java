package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.FileImage;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Ingredient;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.IngredientRepository;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.services.DBFileStorageService;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.services.FileStorageException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Ingredients")
public class IngredientController {

    @Autowired
    private final IngredientRepository ingredientRepository;
    private Logger logger = Logger.getLogger(IngredientController.class);
    @Autowired
    private DBFileStorageService dbFileStorageService;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<Ingredient> getList() {
        List<Ingredient> ingredients = (List<Ingredient>) ingredientRepository.findAll();
        return ingredients;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Ingredient afficherIngredient(@PathVariable int id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        return ingredient.get();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public Ingredient createIngredient(@RequestBody Ingredient newIngredient) {
        logger.debug(newIngredient.getRecetteId());
       newIngredient=ingredientRepository.save(newIngredient);
       return newIngredient;
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    public Ingredient updateIngredient(@RequestBody Ingredient newIngredient) {
        //TODO: implement update
        return ingredientRepository.save(newIngredient);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json")
    public Ingredient delete(@PathVariable int id) {
        Ingredient ingredient=ingredientRepository.findById(id).get();
        ingredientRepository.delete(ingredient);
        return ingredient;
    }

}
