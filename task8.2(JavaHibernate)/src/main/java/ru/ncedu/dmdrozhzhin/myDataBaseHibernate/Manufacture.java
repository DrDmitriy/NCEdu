package ru.ncedu.dmdrozhzhin.myDataBaseHibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Manufacture")
public class Manufacture {
    private String name;
    private String county;
    private String address;
    private String phone_number;
    private int manufacturer_id;
    private List<Products> productsList;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "manufacturer_id", nullable = false, insertable = true, updatable = true)
    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }


    @OneToMany(mappedBy = "manufacturs", cascade = CascadeType.ALL, orphanRemoval = false)
    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "county")
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
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


    public Manufacture() {

    }

    public Manufacture(String name, String county, String address, String phone_number) {
        this.name = name;
        this.address = address;
        this.county = county;
        this.phone_number = phone_number;
        productsList = new ArrayList<>();
    }

    public void addProduct(Products products) {
        productsList.add(products);
    }

    public void removeProduct(Products products) {
        productsList.remove(products);

    }

    @Override
    public String toString() {
        return "manufacturer id = " + manufacturer_id +
                "name " + name +
                "country " + county +
                "phone number " + phone_number;
    }
}
