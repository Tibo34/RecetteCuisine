package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;

public class DAORepository {

    private static DAORepository  instance=null;
    private SessionFactory sessionFactory;
    private Session session;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    private DAORepository(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session=sessionFactory.openSession();
    }

    public static DAORepository getDAORepository(){
        if(instance==null){
            instance=new DAORepository();
        }
        return instance;
    }
}
