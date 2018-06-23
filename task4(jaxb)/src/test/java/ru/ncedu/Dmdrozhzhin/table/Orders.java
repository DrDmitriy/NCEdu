package ru.ncedu.Dmdrozhzhin.table;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;


public class Orders {
    private int order_id;
    @XmlElementWrapper
    @XmlElement(name = "product")
    private List<Products> productIdList = new ArrayList<Products>();
    private int price;
    @XmlTransient
    private Customers customer;

    public Orders(int order_id, int price, Customers customer) {
        this.order_id = order_id;
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

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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
                "order_id=" + order_id +
                ", productIdList=" + productIdList +
                ", price=" + price +
                ", customer=" + customer +
                '}';
    }
}
