package ru.ncedu.dmdrozhzhin.xmlSerealization.table;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;


public class Orders {
    private int orderId;
    @XmlElementWrapper
    @XmlElement(name = "product")
    private List<Products> productIdList = new ArrayList<Products>();
    private int price;
    @XmlTransient
    private Customers customer;

    public Orders(int orderId, int price, Customers customer) {
        this.orderId = orderId;
        this.price = price;
        this.customer = customer;
    }

    public Orders() {
    }

    @XmlTransient
    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public int getorderId() {
        return orderId;
    }

    public void setorderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Products> getProductIdList() {
        return productIdList;
    }

    public void addToProductIdList(Products product) {
        this.productIdList.add(product);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", productIdList=" + productIdList +
                ", price=" + price +
                ", customer=" + customer +
                '}';
    }
}
