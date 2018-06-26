package ru.ncedu.dmdrozhzhin.queueSortThread;

public class Main {
    static QueueArray queueArray;

    public static void main(String[] args) {
        queueArray = new QueueArray();
        FillingQueue fillingQueue = new FillingQueue(queueArray, Integer.valueOf(args[1]));
        Thread fiilingThread = new Thread(fillingQueue);
        fiilingThread.start();

        for (int i = 1; i < Integer.valueOf(args[0]); i++) {
            Thread processorThread = new Thread(new ProccesorQueue(queueArray));
            processorThread.setName("Обработчик " + i);
            processorThread.start();
        }

    }
}
