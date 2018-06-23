package ru.ncedu.Dmdrozhzhin;

import ru.ncedu.Dmdrozhzhin.table.Customers;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JabxImpl {

    public void convertObjectToXml(Customers customers, String filename) {
        try {
            JAXBContext context = JAXBContext.newInstance(Customers.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(customers, new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Customers fromXmlToObject(String filePath) {
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
