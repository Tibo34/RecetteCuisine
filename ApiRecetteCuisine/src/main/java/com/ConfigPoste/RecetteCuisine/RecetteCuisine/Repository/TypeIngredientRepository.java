package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.TypeIngredientEntity;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.UniteEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class TypeIngredientRepository {

    private EntityManager em = repositoryDAO.getEm();
    private List<TypeIngredientEntity> ingredientTypes;

    public TypeIngredientRepository(){
    }

    public List<TypeIngredientEntity> getAll(){
        ingredientTypes =em.createQuery("select u from TypeIngredientEntity u").getResultList();
        return ingredientTypes;
    }

    public TypeIngredientEntity getById(int id){
        TypeIngredientEntity type=em.find(TypeIngredientEntity.class,id);
        return type;
    }
}
