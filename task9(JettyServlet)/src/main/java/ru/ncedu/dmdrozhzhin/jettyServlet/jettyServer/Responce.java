package ru.ncedu.dmdrozhzhin.jettyServlet.jettyServer;

public class Responce {
    public static String regestryResponce(int code) {
        String responce;
        return responce =
                "<?xml version=”1.0” encoding=”utf-8”?>\n" +
                        "<response>\n" +
                        "<result-code>" + code + "</ result-code>\n" +
                        "</response>";
    }

    public static String BalanceResponce(int code, Double newBalance) {
        String responce =
                "<?xml version=”1.0” encoding=”utf-8”?>\n"
                        + "<response>\n" +
                        "<code>" + code + "</code>\n" +
                        "<balance>" + newBalance + "</balance>\n" +
                        "</response>";
        return responce;
    }
}
