package utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author Otto
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final SessionFactory sessionBM;
    private static final SessionFactory sessionCAM;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");
            Configuration configBM = new Configuration();
            configBM.configure("hibernateBM.cfg.xml");
            Configuration configCAM = new Configuration();
            configCAM.configure("hibernateCAM.cfg.xml");
            sessionBM = configBM.buildSessionFactory();
            sessionCAM = configCAM.buildSessionFactory();
            sessionFactory = config.buildSessionFactory();
            //sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static SessionFactory getSessionFactory(int id_empresa){
        if(id_empresa == 2){
            return sessionBM;
        }else{
            return sessionCAM;
        }
        //return sessionGeneral;
    }
}
