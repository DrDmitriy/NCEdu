package ru.ncedu.dmdrozhzhin.QueueSheduledSort;

import java.util.concurrent.*;

public class Main {
    private static BlockingQueue<Integer[]> blockingQueue;

    public static void main(String[] args) {
        int capasity = Integer.valueOf(args[0]);
        int delayTimeProducer = Integer.valueOf(args[1]);
        int delayTimeConsumer = Integer.valueOf(args[2]);

        blockingQueue = new ArrayBlockingQueue<Integer[]>(capasity);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

        ScheduledThreadPoolExecutor producerExecutor = new ScheduledThreadPoolExecutor(1);
        ScheduledThreadPoolExecutor consumerExecutor = new ScheduledThreadPoolExecutor(1);

        producerExecutor.scheduleWithFixedDelay(producer, 0, delayTimeProducer, TimeUnit.SECONDS);
        consumerExecutor.scheduleWithFixedDelay(consumer, 0, delayTimeConsumer, TimeUnit.SECONDS);
    }
}
