package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class repositoryDAO {


    private EntityManager em;
    private static repositoryDAO instance = null;


    private repositoryDAO() {
        try {
            EntityManagerFactory enf = Persistence.createEntityManagerFactory("com.ConfigPoste.RecetteCuisine.RecetteCuisine.persistence");
            em = enf.createEntityManager();
            if(em.isOpen()) {

            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return EntityManager
     */
    public static EntityManager getEm() {
        if (instance == null) {
            instance = new repositoryDAO();
        }
        return instance.em;
    }

}
