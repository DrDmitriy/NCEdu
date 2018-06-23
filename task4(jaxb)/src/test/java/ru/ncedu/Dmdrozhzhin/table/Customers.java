package ru.ncedu.Dmdrozhzhin.table;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Customers {

    private int customer_id;
    private String name;
    private String address;
    private String phone_number;
    @JsonIgnore
    private List<Orders> ordersList = new ArrayList<Orders>();

    public Customers() {
    }

    public Customers(int customer_id, String name, String address, String phone_number) {
        this.customer_id = customer_id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    @XmlElementWrapper
    @XmlElement(name = "order")
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void addToOrdersList(Orders order) {
        this.ordersList.add(order);
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customer_id=" + customer_id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}
