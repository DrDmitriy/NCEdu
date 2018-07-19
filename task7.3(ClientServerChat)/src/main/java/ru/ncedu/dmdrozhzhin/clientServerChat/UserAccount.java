package ru.ncedu.dmdrozhzhin.clientServerChat;

import java.beans.Transient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserAccount {
    public Boolean isRegistry() {
        return isRegistry;
    }

    public String getEmail() {
        return email;
    }

    public void setRegistry(Boolean registry) {
        isRegistry = registry;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Boolean isRegistry;
    private String login;
    private String password;
    private String email;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Transient
    @Override
    public String toString() {
        return "UserAccount{" +
                "isRegistry=" + isRegistry +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public UserAccount() {

    }

    @Transient
    public UserAccount signIn() {

        try {
            System.out.println("Введите логин: ");
            this.login = reader.readLine();
            System.out.println("Введите пароль: ");
            this.password = reader.readLine();
            this.isRegistry = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Transient
    public UserAccount registry() {
        UserAccount userAccount = signIn();
        try {
            System.out.println("Введите email");
            userAccount.email = reader.readLine();
            userAccount.isRegistry = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userAccount;
    }
}
