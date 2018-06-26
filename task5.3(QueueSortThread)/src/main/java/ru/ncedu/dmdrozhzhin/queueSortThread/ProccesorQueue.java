package ru.ncedu.dmdrozhzhin.queueSortThread;

public class ProccesorQueue implements Runnable{
    QueueArray queueArray;

    public ProccesorQueue(QueueArray queueArray) {
        this.queueArray = queueArray;
    }

    public void processNextArray(){
        synchronized (queueArray){
            System.out.println("Обработчик заблоктровал queueArray ");
            if (queueArray.getQueueIntegerMas().size() > 0) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queueArray.getNextArray();

            } else {
                try {
                    System.out.println("Вызываю wait в processNextArray");
                    queueArray.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void run() {
        while (true){
            processNextArray();
        }
    }
}
