package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.TypeIngredient;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.TypeIngredientRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/TypeIngredients")
public class TypeIngredientController {

    @Autowired
    private final TypeIngredientRepository TypeIngredientRepository;
    private Logger logger = Logger.getLogger(TypeIngredientController.class);

    public TypeIngredientController(TypeIngredientRepository typeIngredientRepository) {
        TypeIngredientRepository = typeIngredientRepository;
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value="", method= RequestMethod.GET,produces = "application/json")
    public List<TypeIngredient> getList(){
        List<TypeIngredient> ingredients= (List<TypeIngredient>) TypeIngredientRepository.findAll();
        return ingredients;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = "application/json")
    public TypeIngredient afficherIngredient(@PathVariable int id) {
        Optional<TypeIngredient> ingredient=TypeIngredientRepository.findById(id);
        return ingredient.get();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/create",method = RequestMethod.POST,produces = "application/json")
    public TypeIngredient createIngredient(@RequestBody TypeIngredient newIngredient){
        return TypeIngredientRepository.save(newIngredient);
    }

}
