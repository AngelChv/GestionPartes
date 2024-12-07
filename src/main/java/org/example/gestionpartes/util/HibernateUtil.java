package org.example.gestionpartes.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Crea fábricas de sesiones.
 * Y abre y cierra sesiones.
 */
public class HibernateUtil {
    private static SessionFactory factory;

    // Constructor privado para evitar instancias externas
    private HibernateUtil() {}

    /**
     * Si no hay un {@link SessionFactory} se crea uno con la configuración indicada en el archivo:
     * "configuration/hibernate.cfg.xml"
     * @return {@link SessionFactory}
     */
    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            try {
                Configuration cfg = new Configuration();
                cfg.configure("configuration/hibernate.cfg.xml");
                factory = cfg.buildSessionFactory();
                System.out.println("Fábrica de sesiones creada.");
            } catch (HibernateException e) {
                AlertShow.error("Error al crear la fábrica de sesiones: \n" + e.getMessage());
            }
        }
        return factory;
    }


    /**
     * Obtiene o crea un {@link SessionFactory} mediante el cual abre una {@link Session}
     * @return {@link Session}
     */
    public static Session getSession() {
            return getSessionFactory().openSession();
    }

    public static void closeSessionFactory() {
        if (factory != null) {
            try {
                factory.close();
                System.out.println("Fábrica de sesiones cerrada.");
            } catch (HibernateException e) {
                AlertShow.error("Error al cerrar la fábrica de sesiones: \n" + e.getMessage());
            } finally {
                factory = null;
            }
        }
    }

}
