package ru.ncedu.dmdrozhzhin.queueSortThread;

import java.util.LinkedList;
import java.util.Queue;

public class QueueArray {

    private Queue<Integer[]> queueIntegerMas;

    QueueArray() {
        queueIntegerMas = new LinkedList<Integer[]>();
    }

    public Queue<Integer[]> getQueueIntegerMas() {
        return queueIntegerMas;
    }

    public void addQueue(Integer[] array) {
        queueIntegerMas.add(array);
    }

    public Integer[] getNextArray() {
        Integer[] array = queueIntegerMas.poll();
        return array;
    }
}
