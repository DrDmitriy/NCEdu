package ru.ncedu.Dmdrozhzhin;

import ru.ncedu.Dmdrozhzhin.table.Customers;
import ru.ncedu.Dmdrozhzhin.table.Manufactures;
import ru.ncedu.Dmdrozhzhin.table.Orders;
import ru.ncedu.Dmdrozhzhin.table.Products;


public class GenerateCustomer {
    private static int customer_id = 1;
    private static int order_id = 1;
    private static int manufactures_id = 1;
    private static int product_id = 1;

    public static Customers generateCustomer(){
        Customers customers = new Customers(customer_id, "customer"+customer_id, "Moscow Red Square", "8-915-123-34-53");
        customer_id++;

        Orders order = new Orders(order_id++, (int)Math.random()*1000, customers);
        Manufactures manufactures = new Manufactures(manufactures_id, "manufacture"+manufactures_id,
                "Russia","Moscow,Tverskaya streer", "8-800-888-11-22");
        manufactures_id++;

        Products products = new Products(product_id, manufactures, "product"+product_id,
                Math.random()*100, "category"+Math.random()+10,
                true, "20.06.2018", "10x10");

        order.addToProductIdList(products);
        customers.addToOrdersList(order);

        return customers;
    }
}
