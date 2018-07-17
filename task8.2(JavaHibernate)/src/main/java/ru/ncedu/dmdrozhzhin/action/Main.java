package ru.ncedu.dmdrozhzhin.action;

import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Customers;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Manufacture;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Orders;
import ru.ncedu.dmdrozhzhin.myDataBaseHibernate.Products;
import ru.ncedu.dmdrozhzhin.userInterface.UserService;

public class Main {
    public static void main(String[] args){
        Manufacture manufacture = new Manufacture("HP","USA","some_address","123-456-789");
        Customers customers = new Customers("Ivan","Moscow, Nikolskaya street","8-923-234-56-78");

        Orders orders = new Orders(3500,customers,1);
        Products products1 = new Products(manufacture,orders,"IdeaPad1130",35000,"laptop",true,2017,"10x5x3");


        UserService userService = new UserService();
        userService.addManufacture(manufacture);
       /* userService.addProduct(products1);
        userService.addCustomer(customers);
        userService.addOrder(orders);
*/



        }
}
