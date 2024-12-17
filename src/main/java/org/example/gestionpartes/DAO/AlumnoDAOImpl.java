package org.example.gestionpartes.DAO;

import org.example.gestionpartes.model.Alumno;
import org.example.gestionpartes.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AlumnoDAOImpl implements AlumnoDAO {
    @Override
    public List<Alumno> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "from Alumno";
            return session.createQuery(hql, Alumno.class).list();
        } catch (HibernateException he) {
            return null;
        }
    }

    @Override
    public Alumno findByNumExp(int numExp) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "from Alumno where numExpediente = :numExp";
            Query<Alumno> query = session.createQuery(hql, Alumno.class);
            query.setParameter("numExp", numExp);
            return query.uniqueResult();
        } catch (HibernateException he) {
            return null;
        }
    }
}
