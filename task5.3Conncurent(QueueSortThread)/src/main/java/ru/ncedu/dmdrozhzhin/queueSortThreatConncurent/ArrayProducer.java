package ru.ncedu.dmdrozhzhin.queueSortThreatConncurent;

import ru.ncedu.dmdrozhzhin.quicksort.GenerateRandomArray;

import java.util.concurrent.BlockingQueue;

public class ArrayProducer implements Runnable {
    private BlockingQueue blockingQueue;

    public ArrayProducer(BlockingQueue<Integer[]> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        while (true) {
            try {
                blockingQueue.put(GenerateRandomArray.genIntegerRandomArr(100_000));
                System.out.println("Текущий размер очереди = " + blockingQueue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
