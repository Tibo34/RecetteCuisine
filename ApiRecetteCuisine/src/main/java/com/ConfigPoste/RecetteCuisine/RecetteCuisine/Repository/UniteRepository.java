package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.UniteEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class UniteRepository {

    private EntityManager em = repositoryDAO.getEm();
    private List<UniteEntity> unites;

    public UniteRepository(){
    }

    public List<UniteEntity> getAll(){
        unites =em.createQuery("select u from UniteEntity u").getResultList();
        return unites;
    }

    public UniteEntity getById(int id){
        UniteEntity unite=em.find(UniteEntity.class,id);
        return unite;
    }
}
