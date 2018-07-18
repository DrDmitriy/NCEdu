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


        Orders orders = new Orders(3500,customers,products1);
        //orders.addProduct(products1);
        //orders.addProduct(products2);
        /*ArrayList<Products> productsArrayList = new ArrayList<>();
        productsArrayList.add(products1);
        orders.setProductsList(productsArrayList);


        ArrayList<Orders> ordersArrayList = new ArrayList<>();
        ordersArrayList.add(orders);
        products1.setOrdersList(ordersArrayList);
*/
        UserService userService = new UserService();
        userService.addManufacture(manufacture);
        userService.addProduct(products1);
        userService.addCustomer(customers);
        userService.addOrder(orders);




        }
}
