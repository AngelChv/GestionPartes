package org.example.gestionpartes.DAO;

import org.example.gestionpartes.model.Alumno;
import org.example.gestionpartes.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

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

    public List<Alumno> findByString(String pattern) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "from Alumno " +
                    "where nombre like :pattern or " +
                    "grupo.nombre like :pattern or " +
                    "cast(numExpediente as String) like :pattern or " +
                    "cast(puntos as string) like :pattern";

            return session.createQuery(hql, Alumno.class)
                    .setParameter("pattern", "%" + pattern + "%")
                    .list();
        } catch (HibernateException he) {
            return null;
        }
    }
}
