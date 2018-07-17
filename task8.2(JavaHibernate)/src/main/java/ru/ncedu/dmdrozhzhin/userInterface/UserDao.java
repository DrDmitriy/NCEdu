package ru.ncedu.dmdrozhzhin.userInterface;

import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Customers;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Manufacture;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Orders;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Products;

public interface UserDao {
    public void addManufacture(Manufacture m);

    public void removeManufacture(Manufacture m);

    public Manufacture findManufactureById(int id);

    public void addProduct(Products p);

    public void removeProduct(Products p);

    public Products findProductById(int id);

    public void addOrder(Orders o);

    public void removeOrder(Orders o);

    public Orders findOrderById(int id);

    public void addCustomer(Customers c);

    public Customers findCustomerById(int id);


}
