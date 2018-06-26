package ru.ncedu.dmdrozhzhin.queueSortThread;

import ru.ncedu.dmdrozhzhin.quicksort.GenerateRandomArray;
import ru.ncedu.dmdrozhzhin.quicksort.QuickSort;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Integer[] integerAraay = GenerateRandomArray.genIntegerRandomArr(10);
        QuickSort.comSort(integerAraay);
        QueueArray queueArray = new QueueArray();
        FillingQueue fillingQueue = new FillingQueue(queueArray);
        Thread fiilingThread = new Thread(fillingQueue);
        fiilingThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int n = 0;
        while (true) {
            synchronized (queueArray) {
                //System.out.println( "size before " + queueArray.getQueueIntegerMas().size());
                if (queueArray.getQueueIntegerMas().size() < 5) {
                    try {
                        queueArray.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queueArray.getNextArray();
                }
            }
            // System.out.println( queueArray.getQueueIntegerMas().size());
            n++;

        }


    }
}
