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
        Customers customers1 = GenerateCustomer.generateCustomer();

        convertObjectToXml(customers1, filename);
        Customers customer = fromXmlToObject(filename);

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

    private void convertObjectToXml(Customers customers, String filename) {
        try {
            JAXBContext context = JAXBContext.newInstance(Customers.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(customers, new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static Customers fromXmlToObject(String filePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Customers.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Customers) un.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
