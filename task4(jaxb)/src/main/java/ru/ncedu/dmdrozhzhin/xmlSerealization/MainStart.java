package ru.ncedu.dmdrozhzhin.xmlSerealization;

import ru.ncedu.dmdrozhzhin.xmlSerealization.table.Customers;
import ru.ncedu.dmdrozhzhin.xmlSerealization.table.Orders;
import ru.ncedu.dmdrozhzhin.xmlSerealization.table.Products;
//SDK version 1.7
public class MainStart {
    public static void main(String[] args) {
       new MainStart().jaxbStart(".\\jaxb.xml");
       JacksonImpl.writeValue();
       JacksonImpl.readValue();
    }

    public void jaxbStart(String filename) {
        JabxImpl jabx = new JabxImpl();
        Customers customers1 = GenerateCustomer.generateCustomer();
        jabx.convertObjectToXml(customers1,filename);

        jabx.convertObjectToXml(customers1, filename);
        Customers customer = jabx.fromXmlToObject(filename);

        if (customer != null) {
            System.out.println(customer.toString());
            for (Orders o : customer.getOrdersList()) {
                System.out.println(o.toString());
                for (Products prod : o.getProductIdList()) {
                    System.out.println(prod.toString());
                }
            }
        }
    }
}
