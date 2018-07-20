package ru.ncedu.dmdrozhzhin.myDataBaseHibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Orders")
public class Orders {
    private int order_id;
    private double price;
    Set<Products> productsSet = new HashSet<Products>();
    private Customers customers;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, insertable = true, updatable = true)
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    public Set<Products> getProductsSet() {
        return productsSet;
    }

    public void setProductsSet(Set<Products> productsSet) {
        this.productsSet = productsSet;
    }

    public void addProductToOrder(Products p) {
        productsSet.add(p);
    }


    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }


    public Orders() {

    }

    public Orders(double price, Customers customers) {
        this.price = price;
        this.customers = customers;
    }

}

