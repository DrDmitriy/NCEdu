package dmdrozhzhin.pingPong;

public class Main {

    //public static  boolean flag = false;
    public static void main(String[] args) {

        PingPongCommon pingPongCommon = new PingPongCommon();
        Ping ping = new Ping(pingPongCommon);
        Pong pong = new Pong(pingPongCommon);


        Thread thping =  new Thread(ping);
        Thread thpong=  new Thread(pong);
        thping.start();
      /*  try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        thpong.start();

/*


        System.out.println(thping.isAlive());
        System.out.println(thpong.isAlive());

*/

    }
}
