package org.example.gestionpartes.DAO;

import org.example.gestionpartes.model.Parte;
import org.example.gestionpartes.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class ParteDAOImpl implements ParteDAO {
    @Override
    public List<Parte> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "from Parte";
            return session.createQuery(hql, Parte.class).list();
        } catch (HibernateException he) {
            return null;
        }
    }
}
