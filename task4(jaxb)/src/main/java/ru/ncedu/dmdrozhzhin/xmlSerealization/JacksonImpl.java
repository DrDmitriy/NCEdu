package ru.ncedu.dmdrozhzhin.xmlSerealization;

import org.codehaus.jackson.map.ObjectMapper;
import ru.ncedu.dmdrozhzhin.xmlSerealization.table.Customers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class JacksonImpl {

    public static void writeValue() {
        Customers customers = GenerateCustomer.generateCustomer();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new FileOutputStream(
                    System.getProperty("user.dir") + File.separator
                            + "customer.json"), customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readValue() {
        ObjectMapper mapper = new ObjectMapper();
        String filepath = System.getProperty("user.dir") + File.separator
                + "customer.json";
        try {
            Customers user = (Customers) mapper.readValue(new FileInputStream(filepath),
                    Customers.class);
            System.out.println(user);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
