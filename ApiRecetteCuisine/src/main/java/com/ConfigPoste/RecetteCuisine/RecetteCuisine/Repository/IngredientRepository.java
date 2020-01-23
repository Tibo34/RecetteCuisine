package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Ingredient;
import org.hibernate.Session;

import java.util.List;

public class IngredientRepository {
    private Session session;

    public IngredientRepository(){
        session=DAORepository.getDAORepository().getSession();
    }


    public List<Ingredient> getAll() {
        List<Ingredient> ingredients=session.createQuery("select i from Ingredient i",Ingredient.class).getResultList();
        return ingredients;
    }

    public Ingredient getById(int id) {
        Ingredient ingredient=session.find(Ingredient.class,id);
        return ingredient;
    }

    public Ingredient save(Ingredient newIngredient) {
        session.persist(newIngredient);
        session.flush();
        return newIngredient;
    }
}
