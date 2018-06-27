package ru.ncedu.dmdrozhzhin.queueSortThread;

import ru.ncedu.dmdrozhzhin.quicksort.QuickSort;

public class ProccesorQueue implements Runnable {
    QueueArray queueArray;

    public ProccesorQueue(QueueArray queueArray) {
        this.queueArray = queueArray;
    }

    public void processNextArray() {
        Integer[] nextArray = null;
        synchronized (queueArray) {
            if (queueArray.getQueueIntegerMas().size() > 0) {
                nextArray = queueArray.getNextArray();
                queueArray.notifyAll();
            } else {
                try {
                    queueArray.notifyAll();
                    queueArray.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (nextArray != null) {
            QuickSort.comSort(nextArray);
            System.out.println("Массив успешно обрабоатан");
        }
    }

    public void run() {
        while (true) {
            processNextArray();
        }
    }
}
