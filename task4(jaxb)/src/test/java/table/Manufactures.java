package table;

import java.util.ArrayList;
import java.util.List;

public class Manufactures {
    private int manufacturer_id;
    private String name;
    private String country;

    public Manufactures(int manufacturer_id, String name, String country, String address, String phone_number) {
        this.manufacturer_id = manufacturer_id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phone_number = phone_number;
    }

    private String address;
    private String phone_number;
    private List<Products> productsList = new ArrayList();

    public Manufactures() {
    }
    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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
                "manufacturer_id=" + manufacturer_id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", productsList=" + productsList +
                '}';
    }
}
