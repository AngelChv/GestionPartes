package org.example.gestionpartes.DAO;

import org.example.gestionpartes.model.Profesor;
import org.example.gestionpartes.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ProfesorDAOImpl implements ProfesorDAO {

    @Override
    public Profesor getProfesor(int id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Profesor.class, id);
        }
    }
}
