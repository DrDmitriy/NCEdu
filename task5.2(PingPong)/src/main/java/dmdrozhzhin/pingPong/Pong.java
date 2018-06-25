package dmdrozhzhin.pingPong;

public class Pong implements Runnable{
   PingPongCommon pongFlag;
    public Pong(PingPongCommon pongFlag) {
        this.pongFlag = pongFlag;
    }
    public synchronized void pong() {
        while (true) {
            System.out.println("Старт PONG flag = " + pongFlag.getFlag());

           // while (!pongFlag.getFlag()) {
            while (!pongFlag.getFlag()) {
                // System.out.println(pongFlag.getFlag());

                try {
                   pongFlag.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            System.out.println("pong");
            //Main.flag = false;
            pongFlag.notifyAll();
            pongFlag.setFlag(false);

        }
        //}

    }



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
