package ru.ncedu.dmdrozhzhin.queueSortThread;

import java.util.LinkedList;
import java.util.Queue;

public class QueueArray {
    public Queue<Integer[]> getQueueIntegerMas() {
        return queueIntegerMas;
    }

    private Queue<Integer[]> queueIntegerMas;

    QueueArray() {
        queueIntegerMas = new LinkedList<Integer[]>();
    }

    public void addQueue(Integer[] array) {
        queueIntegerMas.add(array);
    }
}
