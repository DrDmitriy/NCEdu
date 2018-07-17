package ru.ncedu.dmdrozhzhin.myDataBaseHibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int order_id;

  /*  @ManyToMany
    @JoinTable(name = "Products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Products> productsList;*/

    @Column
    private double price;

    //-------------------------------------------------
    @Column
    private int orderNum;
    //------------------------------------------------

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customers customers;

 /*   public void addProductToOrder(Products products) {
        productsList.add(products);
    }
    public void removeProductFromOrder(Products products) {
        productsList.remove(products);
    }
*/
    public Orders(){

    }

    public Orders(double price, Customers customers, int orderNum) {
        this.price = price;
        this.customers = customers;
        this.orderNum = orderNum;
       // productsList = new ArrayList<>();
    }

}

