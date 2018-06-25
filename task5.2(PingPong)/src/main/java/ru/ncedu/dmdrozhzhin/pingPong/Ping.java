package ru.ncedu.dmdrozhzhin.pingPong;

public class Ping implements Runnable {
    Object pingFlag;

    public Ping(Object pingFlag) {
        this.pingFlag = pingFlag;
    }

    public void ping() {
        while (true) {
            synchronized (pingFlag) {
                System.out.println("ping");
                try {
                    pingFlag.notifyAll();
                    pingFlag.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void run() {
        ping();
    }
}

