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

/*   @ManyToMany
    @JoinTable(name = "Products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))*/
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "product_id")
 private Products product;
   //private List<Products> productsList = new ArrayList<>();
  /* public List<Products> getProductsList(){
        return productsList;
    }

    public void setProductsList(List<Products> productsList){

       this.productsList = productsList;
    }

    public void addProduct(Products products) {
       productsList.add(products);
    }
*/

    @Column
    private double price;

  /*  //-------------------------------------------------
    @Column
    private int orderNum;
    //------------------------------------------------
*/
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customers customers;

  /*  public void addProductToOrder(Products products) {
        productsList.add(products);
    }
    public void removeProductFromOrder(Products products) {
        productsList.remove(products);
    }*/

    public Orders(){

    }

    public Orders(double price, Customers customers, Products p) {
        this.product = p;
        this.price = price;
        this.customers = customers;


    }

}

