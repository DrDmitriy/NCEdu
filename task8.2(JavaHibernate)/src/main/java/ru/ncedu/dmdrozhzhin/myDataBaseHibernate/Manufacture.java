package ru.ncedu.dmdrozhzhin.myDataBaseHibernate;

import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Manufacture")
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int manufacturer_id;
    @Column
    private String name;
    @Column
    private String county;
    @Column
    private String address;
    @Column
    private String phone_number;
    @OneToMany(mappedBy = "manufacturs",cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Products> productsList;


    public Manufacture(){

    }

    public Manufacture(String name, String county, String address, String phone_number) {
        this. name = name;
        this.address = address;
        this.county = county;
        this.phone_number = phone_number;
        productsList = new ArrayList<>();
    }

    public void addProduct(Products products) {
        productsList.add(products);
    }

    public void removeProduct(Products products){
        productsList.remove(products);

    }

    @Override
    public String toString() {
        return "manufacturer id = " + manufacturer_id +
                "name " + name +
                "country "+ county+
                "phone number " + phone_number;
    }
}
