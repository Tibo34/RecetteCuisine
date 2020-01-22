package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.RecetteEntity;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.UniteEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class RecetteRepository {

    private EntityManager em = repositoryDAO.getEm();
    private List<RecetteEntity> unites;

    public RecetteRepository(){
    }

    public List<RecetteEntity> getAll(){
        unites =em.createQuery("select r from RecetteEntity r").getResultList();
        return unites;
    }

    public RecetteEntity getById(int id){
        RecetteEntity recette=em.find(RecetteEntity.class,id);
        return recette;
    }
}
