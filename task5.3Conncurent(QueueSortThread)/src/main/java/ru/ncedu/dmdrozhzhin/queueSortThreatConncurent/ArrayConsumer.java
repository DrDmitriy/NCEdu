package ru.ncedu.dmdrozhzhin.queueSortThreatConncurent;

import ru.ncedu.dmdrozhzhin.quicksort.QuickSort;

import java.util.concurrent.BlockingQueue;

public class ArrayConsumer implements Runnable {
    private BlockingQueue blockingQueue;
    public ArrayConsumer(BlockingQueue<Integer[]> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        while (true){
            try {
                Integer[] arrays = (Integer[])blockingQueue.take();
                QuickSort.comSort(arrays);
                System.out.println(Thread.currentThread().getName() +"  успешно обработал массив");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
