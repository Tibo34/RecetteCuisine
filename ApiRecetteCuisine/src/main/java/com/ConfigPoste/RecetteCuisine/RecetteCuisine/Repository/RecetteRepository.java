package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Recette;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RecetteRepository {

    private Session session;

    public RecetteRepository(){
            session=DAORepository.getDAORepository().getSession();

    }

    public List<Recette> getAll(){
        List<Recette> recettes=session.createQuery("SELECT a FROM Recette a", Recette.class).getResultList();
        return recettes;
    }
}
