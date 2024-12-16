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

    @Override
    public Boolean crear(Parte parte) {
        boolean creado = true;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.persist(parte);
            session.getTransaction().commit();
        }catch (HibernateException he) {
            creado = false;
        }
        return creado;
    }

    @Override
    public Boolean editar(Parte parte) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.merge(parte);
            session.getTransaction().commit();
        }catch (HibernateException he) {
            return false;
        }
        return true;
    }


}
