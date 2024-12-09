package org.example.gestionpartes.DAO;

import org.example.gestionpartes.model.Profesor;
import org.example.gestionpartes.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ProfesorDAOImpl implements ProfesorDAO {

    @Override
    public Profesor getProfesor(String numAsig) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "from Profesor where numAsignado = :numAsig";
            return session.createQuery(hql, Profesor.class)
                    .setParameter("numAsig", numAsig)
                    .uniqueResult();
        }catch (HibernateException he) {
            return null;
        }
    }//getProfesor

    @Override
    public Boolean crearProfesor(Profesor profesor) {
        boolean crearProfesor = true;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.persist(profesor);
            session.getTransaction().commit();
        }catch (HibernateException he) {
            crearProfesor = false;
        }
        return crearProfesor;
    }//crearProfesor


}//ProfesorDAOImpl
