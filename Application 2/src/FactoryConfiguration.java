import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

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










    /*private static FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(Item.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration(): factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();

    }*/

}
