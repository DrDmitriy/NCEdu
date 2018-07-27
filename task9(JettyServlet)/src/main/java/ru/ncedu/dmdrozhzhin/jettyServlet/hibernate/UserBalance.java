package ru.ncedu.dmdrozhzhin.jettyServlet.hibernate;

import ru.ncedu.dmdrozhzhin.jettyServlet.hibernate.Customer;
import javax.persistence.*;

@Entity
@Table(name = "userBalance")
public class UserBalance {
    private int id;
    private Customer customer;
    private Double balance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userBalance")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "balance")
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
