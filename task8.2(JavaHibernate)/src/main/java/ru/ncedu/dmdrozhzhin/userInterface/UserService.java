package ru.ncedu.dmdrozhzhin.userInterface;

import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Customers;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Manufacture;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Orders;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Products;

public class UserService {
    private UserDao userDao = new UserDaoImpl();

    public void addManufacture(Manufacture m){
        userDao.addManufacture(m);

    }

    public void removeManufacture(Manufacture m){
        userDao.removeManufacture(m);
    }

    public Manufacture findManufactureById(int id){
        return userDao.findManufactureById(id);
    }


    public void addProduct(Products p){
        userDao.addProduct(p);
    }

    public void removeProduct(Products p){
        userDao.removeProduct(p);
    }

    public Products findProductById(int id){
       return userDao.findProductById(id);
    }

    public void addOrder(Orders o){
        userDao.addOrder(o);
    }

    public void removeOrder(Orders o){
        userDao.removeOrder(o);
    }

    public Orders findOrderById(int id){
       return userDao.findOrderById(id);
    }

    public void addCustomer(Customers c){
        userDao.addCustomer(c);
    }

    public Customers findCustomerById(int id){
        return userDao.findCustomerById(id);
    }

}
