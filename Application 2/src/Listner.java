import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Listner {
    public static void main(String[] args) throws Exception, HibernateException {

        /*Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Customer.class);

        Session session = configuration.buildSessionFactory().openSession();
        Transaction transaction =session.beginTransaction();
        Customer customerDTO = new  Customer(1,"sherul","Galle");
        session.save(customerDTO);
        transaction.commit();
        session.close();*/

        /*Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        *//*Customer customerDTO = new  Customer(1,"sherul","Galle");
        session.save(customerDTO);*//*
        String hql = "from Customer";
        List<Customer> Customerlist = session.createQuery(hql).list();

        for (Customer customer : Customerlist) {
            System.out.println(customer.getId()+customer.getName());
        }

        tx.commit();
        session.close();*/

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer(1, "sherul", "gallet");
        System.out.println("before session update");
        session.update(customer);
        transaction.commit();
        session.close();




    }
}
