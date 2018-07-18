package ru.ncedu.dmdrozhzhin.clientServerChat;

public class Message {
    private String loginFrom;

    public String getLoginFrom() {
        return loginFrom;
    }

    public void setLoginFrom(String loginFrom) {
        this.loginFrom = loginFrom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLoginTo() {
        return loginTo;
    }

    public void setLoginTo(String loginTo) {
        this.loginTo = loginTo;
    }

    private String message;
    private String loginTo;

    public Message(String loginFrom, String message, String loginTo) {
        this.loginFrom = loginFrom;
        this.message = message;
        this.loginTo = loginTo;
    }
    public  Message() {}
}
