package ru.ncedu.dmdrozhzhin.jettyServlet.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDaoImpl {
    SessionFactory sessionFactory = HibernateSeesion.getSessionFactory();

    public void close() {
        //sessionFactory.close();
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }

    public Boolean isLoginExist(String login) {
        Customer customer = findCustomerByLogin(login);
        if (customer != null) {
            return true;
        }
        return false;
    }

    private Customer findCustomerByLogin(String login) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Customer.class)
                .add(Restrictions.eq("login", login));
        List list = criteria.list();
        session.close();
        if (list.size() > 0) {
            return (Customer) list.get(0);
        } else {
            return null;
        }
    }

    public void addNewCustomer(Customer customer) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    public Double getBalance(String login) {
        Session session = getSession();
        Customer customer = findCustomerByLogin(login);
        session.close();
        return customer.getUserBalance().getBalance();

    }

    public void setBalance(String login, Double newBalance) {
        Session session = getSession();
        Customer customer = findCustomerByLogin(login);
        UserBalance balance = customer.getUserBalance();
        balance.setBalance(newBalance);
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }
    public boolean checkPassword(String login, String passForCheck) {
        Customer customer = findCustomerByLogin(login);
        return customer.getPassword().equals(passForCheck);
    }
}
