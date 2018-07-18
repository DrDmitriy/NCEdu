package ru.ncedu.dmdrozhzhin.action;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Customers;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Manufacture;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Orders;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Products;


public class MyHibernateSessionFactoryUtill {
    private static SessionFactory sessionFactory;


    private MyHibernateSessionFactoryUtill(){

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                //configure() - записывает настройки из Hibernate.cfg.xml
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Customers.class);
                configuration.addAnnotatedClass(Orders.class);
                configuration.addAnnotatedClass(Products.class);
                configuration.addAnnotatedClass(Manufacture.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            }
            catch (Exception e) {
                System.out.println("Exception" + e);
            }

        }
        return sessionFactory;
    }
}
