package dmdrozhzhin.pingPong;

public class Ping implements Runnable {
    // private static boolean flag = false;

   PingPongCommon pingFlag;
    public Ping(PingPongCommon pingFlag) {
        this.pingFlag = pingFlag;
    }
    public synchronized void ping() {
        while (true) {
            System.out.println("Старт PING flag = " + pingFlag.getFlag());
            //while (pingFlag.getFlag()) {
            while (pingFlag.getFlag()) {

                    try {
                        pingFlag.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //if (!Main.flag) {
            pingFlag.notifyAll();
                System.out.println("ping");
                 pingFlag.setFlag(true);
                //Main.flag = true;

                //}
            }
        }


/*

    public void  pong(){
        if(flag) {
            System.out.println("pong");
            flag = false;
        }
    }
*/

    public void run() {

            System.out.println("run - PING");
            ping();

    }
}

