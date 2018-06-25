package ru.ncedu.dmdrozhzhin.pingPong;

public class Pong implements Runnable {
    Object pongFlag;

    public Pong(Object pongFlag) {
        this.pongFlag = pongFlag;
    }

    public void pong() {
        while (true) {
            synchronized (pongFlag) {
                System.out.println("pong");
                try {
                    pongFlag.notifyAll();
                    pongFlag.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void run() {
        pong();
    }
}
