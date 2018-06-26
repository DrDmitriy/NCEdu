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
          /*  try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println(Thread.currentThread().getName() + "  заблоктровал queueArray ");
            if (queueArray.getQueueIntegerMas().size() > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nextArray = queueArray.getNextArray();


            } else {
                try {
                    System.out.println("Вызываю wait в processNextArray");
                    queueArray.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
        if(nextArray != null) {
            QuickSort.comSort(nextArray);
        }
    }

    public void run() {
        while (true) {
            processNextArray();
        }
    }
}
