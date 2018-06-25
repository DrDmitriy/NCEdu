package dmdrozhzhin.pingPong;

public class Ping implements Runnable {
    // private static boolean flag = false;

    Object pingFlag;

    public Ping(Object pingFlag) {
        this.pingFlag = pingFlag;
    }

    public void ping() {
        while (true) {
            synchronized (pingFlag) {
                //System.out.println("Старт PING flag = " + pingFlag.getFlag());
                //while (pingFlag.getFlag()) {
           /* while (pingFlag.getFlag()) {

                    try {
                        pingFlag.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
                //if (!Main.flag) {
                System.out.println("ping");
                try {pingFlag.notifyAll();

                    pingFlag.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            //pingFlag.setFlag(true);
            //Main.flag = true;

            //}
        }
    }


    public void run() {

        System.out.println("run - PING");
        ping();

    }
}

