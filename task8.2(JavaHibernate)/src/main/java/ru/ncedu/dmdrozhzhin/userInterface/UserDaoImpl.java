package ru.ncedu.dmdrozhzhin.userInterface;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.ncedu.dmdrozhzhin.action.MyHibernateSessionFactoryUtill;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Customers;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Manufacture;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Orders;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Products;

public class UserDaoImpl implements UserDao {
    private Session getSession(){
        return MyHibernateSessionFactoryUtill.getSessionFactory().openSession();
    }
    @Override
    public void addManufacture(Manufacture m) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(m);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeManufacture(Manufacture m) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(m);
        transaction.commit();
        session.close();

    }

    @Override
    public Manufacture findManufactureById(int id) {
        Session session = getSession();
        Manufacture manufacture = session.get(Manufacture.class, id);
        session.close();
        return manufacture;
    }

    @Override
    public void addProduct(Products p) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(p);
        transaction.commit();
        session.close();

    }

    @Override
    public void removeProduct(Products p) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(p);
        transaction.commit();
        session.close();
    }

    @Override
    public Products findProductById(int id) {
        Session session = getSession();
        Products products = session.get(Products.class, id);
        session.close();
        return products;
    }

    @Override
    public void addOrder(Orders o) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(o);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeOrder(Orders o) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(o);
        transaction.commit();
        session.close();
    }

    @Override
    public Orders findOrderById(int id) {
        Session session = getSession();
        Orders order = session.get(Orders.class, id);
        session.close();
        return order;
    }

    @Override
    public void addCustomer(Customers c) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
        session.close();
    }

    @Override
    public Customers findCustomerById(int id) {
        Session session = getSession();
        Customers customers = session.get(Customers.class, id);
        session.close();
        return customers;
    }
}