package ru.ncedu.dmdrozhzhin.QueueSheduledSort;

import ru.ncedu.dmdrozhzhin.quicksort.QuickSort;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
    BlockingQueue blockingQueue;

    public Consumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    private void consum() {
        Integer[] arrays = null;
        try {
            arrays = (Integer[]) blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        QuickSort.comSort(arrays);
        System.out.println("Consumer успешно обработал массив. Длина очереди= " + blockingQueue.size());
    }

    public void run() {
        consum();
    }
}
