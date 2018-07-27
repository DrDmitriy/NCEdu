package ru.ncedu.dmdrozhzhin.jettyServlet.jettyServer;

import ru.ncedu.dmdrozhzhin.jettyServlet.userDao.UserAction;

public class ActionUserProfile {
    private String action;
    private String login;
    private String pass;
    private Double newBalance;
    private Double currentBalance;
    private int responseCode;
    private String responce;

    public ActionUserProfile(String action, String login, String pass, String balance) {
        this.action = action;
        this.login = login;
        this.pass = pass;
        if (balance != null && !balance.equals("")) {
            this.newBalance = Double.valueOf(balance);
        }
    }

    public void execute() {
        if (action.equals("registerCustomer")) {
            this.responseCode = UserAction.registerCustomer(this);
            responce = Responce.regestryResponce(responseCode);

        }
        else if (action.equals("setBalance")) {
            this.responseCode = UserAction.setBalance(this);
            responce = Responce.BalanceResponce(responseCode,currentBalance);

        }
        else if (action.equals("getBalance")) {
            this.responseCode = UserAction.getBalance(this);
            responce = Responce.BalanceResponce(responseCode,currentBalance);

        }
        else {
            this.responseCode = 4;
            responce = Responce.regestryResponce(responseCode);
        }
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

    public Double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(Double newBalance) {
        this.newBalance = newBalance;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    public String getResponce() {
        return responce;
    }

    public void setResponce(String responce) {
        this.responce = responce;
    }
}
