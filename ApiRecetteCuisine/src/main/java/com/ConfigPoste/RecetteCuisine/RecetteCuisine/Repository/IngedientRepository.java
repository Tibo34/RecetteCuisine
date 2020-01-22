package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.IngredientEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class IngedientRepository {
    private EntityManager em = repositoryDAO.getEm();
    private List<IngredientEntity> ingredients;

    public IngedientRepository(){
    }

    public List<IngredientEntity> getAll(){
        ingredients =em.createQuery("select u from IngredientEntity u").getResultList();
        return ingredients;
    }

    public IngredientEntity getById(int id){
        IngredientEntity ingredient=em.find(IngredientEntity.class,id);
        return ingredient;
    }
}
