package ru.ncedu.dmdrozhzhin.jettyServlet.userClient;

import java.util.Scanner;

public class UserClient {
    private String action;
    private String login;
    private String pass;
    private String balance;
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Choose:");
        System.out.println("1.Create account\n" + "2.Get Balance\n" + "3.Set balance");
        String choose = scanner.nextLine();
        if (choose.equals("1") || choose.equals("2") || choose.equals("3")) {
            action = choose;
            readLogin();
            if (action.equals("1") || action.equals("2")) {
                readPassword();
            } else {
                readnewBalance();
            }
        } else {
            System.out.println("Incorrect command");
            start();
            return;
        }
        transformAction();
    }

    private void readnewBalance() {
        System.out.println("balance:");
        String stringBalance = scanner.next();
        try {
            Double.valueOf(stringBalance);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect format");
            readnewBalance();
            return;
        }
        balance = stringBalance;
    }

    private void readLogin() {
        System.out.println("login:");
        login = scanner.next();
    }
    private void readPassword() {
        System.out.println("password:");
        pass = scanner.next();
    }

    public String getAction() {
        return action;
    }

    public void transformAction() {
        if (action.equals("1")) {
            action = "registerCustomer";
        } else if (action.equals("2")) {
            action = "getBalance";
        } else if (action.equals("3")) {
            action = "setBalance";
        }
    }
    public void setAction(String action) {
        this.action = action;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
