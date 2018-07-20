package ru.ncedu.dmdrozhzhin.myDataBaseHibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Products")
public class Products {
    private int product_id;
    private Manufacture manufacturs;
    private String name;
    private double price;
    private String category;
    private boolean is_available;
    private int year_production;
    private String size;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, insertable = true, updatable = true)
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    private Set<Orders> ordersSet = new HashSet<Orders>();

    @ManyToMany
    @JoinTable(name = "orders_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))

    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    public Manufacture getManufacturs() {
        return manufacturs;
    }

    public void setManufacturs(Manufacture manufacturs) {
        this.manufacturs = manufacturs;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "is_available")
    public boolean isIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    @Column(name = "year_production")
    public int getYear_production() {
        return year_production;
    }

    public void setYear_production(int year_production) {
        this.year_production = year_production;
    }

    @Column(name = "size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    public Products(Manufacture manufacturs, String name, double price, String category, boolean is_available, int year_production, String size) {
        this.manufacturs = manufacturs;
        this.name = name;
        this.price = price;
        this.category = category;
        this.is_available = is_available;
        this.year_production = year_production;
        this.size = size;
    }

    public Products() {

    }
}
