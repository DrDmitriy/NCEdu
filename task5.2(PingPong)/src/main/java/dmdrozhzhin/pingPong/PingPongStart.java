package dmdrozhzhin.pingPong;

public class PingPongStart {
    public static void main(String[] args) {
        Object lock = new Object();
        Ping ping = new Ping(lock);
        Pong pong = new Pong(lock);
        new Thread(ping).start();
        new Thread(pong).start();
    }
}
