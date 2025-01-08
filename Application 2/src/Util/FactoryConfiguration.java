package Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class FactoryConfiguration {

    private static SessionFactory sessionFactory;

    private FactoryConfiguration() {}

    public static void setsessionFactory(SessionFactory sFactory) {
        if (sessionFactory == null) {
            sessionFactory = sFactory;
        }
    }

    public static Session getSession() {
        if (sessionFactory == null) {
            throw new IllegalStateException("Factory is not initialized");
        }
        return sessionFactory.openSession();
    }










    /*private static Util.FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;

    private Util.FactoryConfiguration(){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Entity.Customer.class).addAnnotatedClass(Entity.Item.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static Util.FactoryConfiguration getInstance(){
        return factoryConfiguration == null ? factoryConfiguration = new Util.FactoryConfiguration(): factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();

    }*/

}
