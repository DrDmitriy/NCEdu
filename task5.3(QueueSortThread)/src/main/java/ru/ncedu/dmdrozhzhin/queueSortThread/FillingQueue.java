package ru.ncedu.dmdrozhzhin.queueSortThread;

import ru.ncedu.dmdrozhzhin.quicksort.GenerateRandomArray;

public class FillingQueue implements Runnable {
    private static int countAddOperation = 1;
    private QueueArray queueArray;
    private int capasity;

    FillingQueue(QueueArray queueArray, int capasity) {
        this.queueArray = queueArray;
        this.capasity = capasity;
    }

    private void generateArrays() {
        Integer[] integerMas = GenerateRandomArray.genIntegerRandomArr(100000);
        addInQueue(integerMas);

    }

    private void addInQueue(Integer[] arrToAdd) {
        synchronized (queueArray) {
            int masSize = queueArray.getQueueIntegerMas().size();
            if (masSize < capasity) {
                countAddOperation++;

                queueArray.addQueue(arrToAdd);
                queueArray.notifyAll();
                System.out.println(" Текущий размер очереди " + queueArray.getQueueIntegerMas().size());
            } else {
                try {
                    queueArray.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void run() {
        while (true) {
            generateArrays();
        }
    }
}
