package ru.ncedu.dmdrozhzhin.queueSortThread;

import java.util.Iterator;
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

    public Iterator<Integer[]> getIterator (){
        return queueIntegerMas.iterator();
    }

    public Integer[] getNextArray(){

            System.out.println("Очередь - 1");
            Integer[] array = queueIntegerMas.poll();
            this.notify();
            return array;

    }
}
