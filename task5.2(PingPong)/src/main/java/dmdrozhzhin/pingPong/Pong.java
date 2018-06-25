package dmdrozhzhin.pingPong;

public class Pong implements Runnable {
    Object pongFlag;

    public Pong(Object pongFlag) {
        this.pongFlag = pongFlag;
    }

    public void pong() {
        while (true) {
            synchronized (pongFlag) {
        /*while (true) {
            System.out.println("Старт PONG flag = " + pongFlag.getFlag());
*/
                // while (!pongFlag.getFlag()) {
          /*  while (!pongFlag.getFlag()) {
                // System.out.println(pongFlag.getFlag());*/
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


    //Main.flag = false;

    // pongFlag.setFlag(false);

    //}
    //}





        /*  if (Main.flag) {
            System.out.println("ping");
            Main.flag = false;
        }

        try {
            wait();
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        }*/

    public void run() {

        System.out.println("run - PONG");
        pong();


    }
}
