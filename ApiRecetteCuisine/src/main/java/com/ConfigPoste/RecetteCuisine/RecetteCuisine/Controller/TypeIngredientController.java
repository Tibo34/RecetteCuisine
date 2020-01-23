package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Ingredient;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.TypeIngredient;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.IngredientRepository;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.TypeIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/TypeIngredients")
public class TypeIngredientController {

    private final TypeIngredientRepository TypeIngredientRepository;

    @Autowired
    TypeIngredientController() {
        this.TypeIngredientRepository = new TypeIngredientRepository();
    }

    @RequestMapping(value="", method= RequestMethod.GET,produces = "application/json")
    public List<TypeIngredient> getList(){
        List<TypeIngredient> ingredients=TypeIngredientRepository.getAll();
        return ingredients;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = "application/json")
    public TypeIngredient afficherIngredient(@PathVariable int id) {
        TypeIngredient ingredient=TypeIngredientRepository.getById(1);
        return ingredient;
    }
    @RequestMapping(value = "/create",method = RequestMethod.POST,produces = "application/json")
    public TypeIngredient createIngredient(@RequestBody TypeIngredient newIngredient){
        return TypeIngredientRepository.save(newIngredient);
    }

}
