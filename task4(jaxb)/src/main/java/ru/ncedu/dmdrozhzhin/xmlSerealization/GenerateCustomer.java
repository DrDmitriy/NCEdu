package ru.ncedu.dmdrozhzhin.xmlSerealization;

import ru.ncedu.dmdrozhzhin.xmlSerealization.table.Customers;
import ru.ncedu.dmdrozhzhin.xmlSerealization.table.Manufactures;
import ru.ncedu.dmdrozhzhin.xmlSerealization.table.Orders;
import ru.ncedu.dmdrozhzhin.xmlSerealization.table.Products;


public class GenerateCustomer {
    private static int customerId = 1;
    private static int orderId = 1;
    private static int manufacturesId = 1;
    private static int productId = 1;

    public static Customers generateCustomer(){
        Customers customers = new Customers(customerId, "customer"+customerId, "Moscow Red Square", "8-915-123-34-53");
        customerId++;

        Orders order = new Orders(orderId++, (int)Math.random()*1000, customers);
        Manufactures manufactures = new Manufactures(manufacturesId, "manufacture"+manufacturesId,
                "Russia","Moscow,Tverskaya streer", "8-800-888-11-22");
        manufacturesId++;

        Products products = new Products(productId, manufactures, "product"+productId,
                Math.random()*100, "category"+Math.random()+10,
                true, "20.06.2018", "10x10");

        order.addToProductIdList(products);
        customers.addToOrdersList(order);

        return customers;
    }
}
