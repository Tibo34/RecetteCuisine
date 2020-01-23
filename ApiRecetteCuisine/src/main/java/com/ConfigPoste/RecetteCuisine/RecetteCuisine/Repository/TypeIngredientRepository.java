package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Ingredient;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.TypeIngredient;
import org.hibernate.Session;

import java.util.List;

public class TypeIngredientRepository {

    private Session session;

    public TypeIngredientRepository(){
        session=DAORepository.getDAORepository().getSession();
    }


    public List<TypeIngredient> getAll() {
        List<TypeIngredient> ingredients=session.createQuery("select i from TypeIngredient i",TypeIngredient.class).list();
        return ingredients;
    }

    public TypeIngredient getById(int id) {
        TypeIngredient ingredient=session.find(TypeIngredient.class,id);
        return ingredient;
    }

    public TypeIngredient save(TypeIngredient newIngredient) {
        session.persist(newIngredient);
        session.flush();
        return newIngredient;
    }
}
