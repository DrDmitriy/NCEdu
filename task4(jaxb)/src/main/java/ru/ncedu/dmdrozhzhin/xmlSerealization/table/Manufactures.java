package ru.ncedu.dmdrozhzhin.xmlSerealization.table;

import java.util.ArrayList;
import java.util.List;

public class Manufactures {
    private int manufacturerId;
    private String name;
    private String country;

    public Manufactures(int manufacturerId, String name, String country, String address, String phoneNumber) {
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    private String address;
    private String phoneNumber;
    private List<Products> productsList = new ArrayList();

    public Manufactures() {
    }

    public int getmanufacturerId() {
        return manufacturerId;
    }

    public void setmanufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Products> getProductsList() {
        return productsList;
    }

    public void addToProductsList(Products product) {
        this.productsList.add(product);
    }

    @Override
    public String toString() {
        return "Manufactures{" +
                "manufacturerId=" + manufacturerId +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", productsList=" + productsList +
                '}';
    }
}
