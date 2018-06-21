import table.Customers;
import table.Manufactures;
import table.Orders;
import table.Products;
import javax.xml.bind.*;
import java.io.File;

public class MainStart {
    public static void main(String[] args) {
        new MainStart().jaxbStart(".\\jaxb.xml");
    }

    public void jaxbStart(String filename) {
        JabxImpl jabx = new JabxImpl();
        Customers customers1 = GenerateCustomer.generateCustomer();

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
