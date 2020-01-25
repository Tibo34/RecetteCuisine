package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Ingredient;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.IngredientRepository;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Ingredients")
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    IngredientController() {
        this.ingredientRepository = new IngredientRepository();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="", method= RequestMethod.GET,produces = "application/json")
    public List<Ingredient> getList(){
        List<Ingredient> ingredients=new IngredientRepository().getAll();
        return ingredients;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = "application/json")
    public Ingredient afficherIngredient(@PathVariable int id) {
        Ingredient ingredient=new IngredientRepository().getById(1);
        return ingredient;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/create",method = RequestMethod.POST,produces = "application/json")
    public Ingredient createIngredient(@RequestBody Ingredient newIngredient){
        return ingredientRepository.save(newIngredient);
    }
}
