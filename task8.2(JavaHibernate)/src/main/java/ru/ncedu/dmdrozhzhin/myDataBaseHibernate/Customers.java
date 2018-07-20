package ru.ncedu.dmdrozhzhin.myDataBaseHibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customers")
public class Customers {
    private String name;


    private String address;
    private String phone_number;
    private List<Orders> ordersList;


    private int customer_id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", nullable = false, insertable = true, updatable = true)
    public int getCustomer_id() {

        return customer_id;
    }

    public void setCustomer_id(int customer_id) {

        this.customer_id = customer_id;
    }


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customers", orphanRemoval = false)
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {

        this.ordersList = ordersList;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "phone_number")
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Customers() {

    }

    public Customers(String name, String address, String phone_number) {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        ordersList = new ArrayList<>();
    }
}
