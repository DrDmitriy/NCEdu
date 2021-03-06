package ru.ncedu.dmdrozhzhin.queueSortThreatConncurent;

import java.util.concurrent.*;

public class Main {

    private static BlockingQueue<Integer[]> blockingQueue;

    public static void main(String[] args) {
        int countPool = Integer.valueOf(args[0]);
        int capasity = Integer.valueOf(args[1]);
        blockingQueue = new ArrayBlockingQueue<Integer[]>(capasity);

        ExecutorService executorService = Executors.newFixedThreadPool(countPool);
        new Thread(new ArrayProducer(blockingQueue)).start();
        for (int i = 0 ; i < countPool; i++) {
            executorService.execute(new ArrayConsumer(blockingQueue));
        }



    }
}
