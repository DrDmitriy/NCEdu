package ru.ncedu.dmdrozhzhin.queueSortThread;

import ru.ncedu.dmdrozhzhin.quicksort.GenerateRandomArray;
import ru.ncedu.dmdrozhzhin.quicksort.QuickSort;

import java.io.File;
import java.util.*;

public class Main {
    static QueueArray queueArray;
    public static void main(String[] args) {
        Integer[] integerAraay = GenerateRandomArray.genIntegerRandomArr(10);
        QuickSort.comSort(integerAraay);
        queueArray = new QueueArray();
        FillingQueue fillingQueue = new FillingQueue(queueArray);
        Thread fiilingThread = new Thread(fillingQueue);

        ProccesorQueue proccesorQueue1 = new ProccesorQueue(queueArray);
        Thread proccesorThread1 = new Thread(proccesorQueue1);



        fiilingThread.start();
        proccesorThread1.start();




        /*while (true) {
            proccesorQueue1.processNextArray();



        *//*    synchronized (queueArray) {
                //System.out.println( "size before " + queueArray.getQueueIntegerMas().size());
                if (queueArray.getQueueIntegerMas().size() < 1) {
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
            n++;*//*

        }*/


    }
  /*  public static void processNextArray(){
        synchronized (queueArray){
            //System.out.println( "size before " + queueArray.getQueueIntegerMas().size());
            if (queueArray.getQueueIntegerMas().size() > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queueArray.getNextArray();

            } else {
                try {
                    queueArray.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }*/
}
