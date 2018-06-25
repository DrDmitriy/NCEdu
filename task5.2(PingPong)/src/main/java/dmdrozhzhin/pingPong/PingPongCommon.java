package dmdrozhzhin.pingPong;

public class PingPongCommon {

    private Boolean flag = false;

    public synchronized void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public  synchronized Boolean getFlag() {
        return flag;
    }


}
