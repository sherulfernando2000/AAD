import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

@WebListener
public class Listner implements ServletContextListener {

    private SessionFactory sessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized");
        try {
             Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
             sessionFactory =  configuration.buildSessionFactory();
             System.out.println(sessionFactory);

             FactoryConfiguration.setsessionFactory(sessionFactory);

            //sce.getServletContext().setAttribute("SessionFactory", sessionFactory);
            System.out.println("hibernate session factory initialized successfully.");

        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Hibernate SessionFactory   ",e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("shutdown hibernate session factory.");
        if (sessionFactory != null) {
                sessionFactory.close();
        }

    }


  /*  public static void main(String[] args) throws Exception, HibernateException {

        *//*Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Customer.class);

        Session session = configuration.buildSessionFactory().openSession();
        Transaction transaction =session.beginTransaction();
        Customer customerDTO = new  Customer(1,"sherul","Galle");
        session.save(customerDTO);
        transaction.commit();
        session.close();*//*

        *//*Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        *//**//*Customer customerDTO = new  Customer(1,"sherul","Galle");
        session.save(customerDTO);*//**//*
        String hql = "from Customer";
        List<Customer> Customerlist = session.createQuery(hql).list();

        for (Customer customer : Customerlist) {
            System.out.println(customer.getId()+customer.getName());
        }

        tx.commit();
        session.close();*//*

        *//*Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer(1, "sherul", "gallet");
        System.out.println("before session update");
        session.update(customer);
        transaction.commit();
        session.close();*//*

    }
*/
}
