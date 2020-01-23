package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Unite;
import org.hibernate.Session;

import java.util.List;

public class UniteRepository {
    private Session session;

    public UniteRepository(){
        session=DAORepository.getDAORepository().getSession();
    }

    public List<Unite> getAll(){
        List<Unite> unites=session.createQuery("SELECT a FROM Unite a", Unite.class).getResultList();
        return unites;
    }

    public Unite getById(int id){
        Unite unite=session.find(Unite.class,id);
        return unite;
    }

    public Unite save(Unite unite){
        session.persist(unite);
        session.flush();
        return unite;
    }
}
