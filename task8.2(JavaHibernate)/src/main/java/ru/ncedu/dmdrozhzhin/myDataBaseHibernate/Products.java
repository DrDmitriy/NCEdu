package ru.ncedu.dmdrozhzhin.myDataBaseHibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Products")
public class Products {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int product_id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "manufacturer_id")
    private Manufacture manufacturs;


/*
    @ManyToMany
    @JoinTable(name = "Orders",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Orders> ordersList = new ArrayList<>();
*/

    @Column
    private String name;
    @Column
    private double price;
    @Column
    private String category;
    @Column
    private boolean is_available;
    @Column
    private int year_production;
    @Column
    private String size;

    public Products(Manufacture manufacturs,Orders o, String name, double price, String category, boolean is_available, int year_production, String size) {
        this.manufacturs = manufacturs;
        this.name = name;
        this.price = price;
        this.category = category;
        this.is_available = is_available;
        this.year_production = year_production;
        this.size = size;
       // ordersList.add(o);
    }

    public Products() {

    }
}
