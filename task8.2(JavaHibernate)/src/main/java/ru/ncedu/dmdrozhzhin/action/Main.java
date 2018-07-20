package ru.ncedu.dmdrozhzhin.action;

import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Customers;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Manufacture;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Orders;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Products;
import ru.ncedu.dmdrozhzhin.userInterface.UserService;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Manufacture manufacture = new Manufacture("HP","USA","some_address","123-456-789");
        Customers customers = new Customers("Ivan","Moscow, Nikolskaya street","8-923-234-56-78");

        Products products1 = new Products(manufacture,"IdeaPad1130",35000,"laptop",true,2017,"10x5x3");
        Products products2 = new Products(manufacture,"IdeaPad2222",35000,"laptop",true,2017,"10x5x3");
        Products products3 = new Products(manufacture,"IdeaPad1111",35000,"laptop",true,2017,"10x5x3");

        Orders orders = new Orders(3500,customers);
        UserService userService = new UserService();
        orders.addProductToOrder(products1);
        orders.addProductToOrder(products2);

        userService.addManufacture(manufacture);
        userService.addCustomer(customers);
        userService.addOrder(orders);
        userService.addProduct(products3);
        //userService.removeOrder(userService.findOrderById(1));
        }
}
