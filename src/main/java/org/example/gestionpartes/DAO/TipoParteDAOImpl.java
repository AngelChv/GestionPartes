package org.example.gestionpartes.DAO;

import org.example.gestionpartes.model.TipoParte;
import org.example.gestionpartes.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class TipoParteDAOImpl implements TipoParteDAO {
    @Override
    public List<TipoParte> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "from TipoParte";
            return session.createQuery(hql, TipoParte.class).list();
        } catch (HibernateException he) {
            return null;
        }
    }
}
