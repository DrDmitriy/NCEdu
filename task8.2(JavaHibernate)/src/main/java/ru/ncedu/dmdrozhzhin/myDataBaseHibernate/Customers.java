package ru.ncedu.dmdrozhzhin.myDataBaseHibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customer_id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String phone_number;
    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Orders> ordersList;

    public void addOrder(Orders orders){
        ordersList.add(orders);
    }
    public void removeOrder(Orders orders){
        ordersList.remove(orders);
    }

    public Customers(){

    }

    public Customers(String name, String address, String phone_number) {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        ordersList = new ArrayList<>();
    }
}
