package ru.ncedu.dmdrozhzhin.jettyServlet.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {
    private int id;
    private String login;
    private String password;
    private UserBalance userBalance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false,insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CustomerBalanceId", referencedColumnName = "id")
    public UserBalance getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(UserBalance userBalance) {
        this.userBalance = userBalance;
    }
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer() {

    }

    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
    }


}
