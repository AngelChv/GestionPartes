package org.example.gestionpartes.DAO;

import org.example.gestionpartes.model.Profesor;
import org.example.gestionpartes.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ProfesorDAOImpl implements ProfesorDAO {

    @Override
    public Profesor getProfesor(String numAsig) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "from Profesor where numAsignado = :numAsig";
            return session.createQuery(hql, Profesor.class)
                    .setParameter("numAsig", numAsig)
                    .uniqueResult();
        }catch (Exception e) {
            return null;
        }
    }
}
