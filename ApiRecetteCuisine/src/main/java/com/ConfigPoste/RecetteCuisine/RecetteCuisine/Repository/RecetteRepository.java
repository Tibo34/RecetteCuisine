package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Recette;
import org.hibernate.Session;

import java.util.List;

public class RecetteRepository {

    private Session session;

    public RecetteRepository(){
            session=DAORepository.getDAORepository().getSession();
    }

    public List<Recette> getAll(){
        List<Recette> recettes=session.createQuery("SELECT a FROM Recette a", Recette.class).list();
        return recettes;
    }

    public Recette getById(int id){
        Recette recette=session.find(Recette.class,id);
        return recette;
    }

    public Recette save(Recette recette){
        session.persist(recette);
        session.flush();
        return recette;
    }
}
