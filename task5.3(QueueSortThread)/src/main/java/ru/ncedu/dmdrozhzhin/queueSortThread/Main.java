package ru.ncedu.dmdrozhzhin.queueSortThread;

import ru.ncedu.dmdrozhzhin.quicksort.GenerateRandomArray;
import ru.ncedu.dmdrozhzhin.quicksort.QuickSort;

import java.io.File;
import java.util.*;

public class Main {
    //static int countThread= 1;
    static QueueArray queueArray;
    public static void main(String[] args) {
        /*Integer[] integerAraay = GenerateRandomArray.genIntegerRandomArr(10000);
        QuickSort.comSort(integerAraay)*/;
        queueArray = new QueueArray();
        FillingQueue fillingQueue = new FillingQueue(queueArray,Integer.valueOf(args[1]));
        Thread fiilingThread = new Thread(fillingQueue);
        fiilingThread.start();

        for(int i = 1; i < Integer.valueOf(args[0]);i++){
            Thread processorThread = new Thread(new ProccesorQueue(queueArray));
            processorThread.setName("Обработчик "+ i);
            processorThread.start();
        }

    /*    ProccesorQueue proccesorQueue1 = new ProccesorQueue(queueArray);
        Thread proccesorThread1 = new Thread(proccesorQueue1);
        proccesorThread1.setName("Обработчик 1");

        ProccesorQueue proccesorQueue2 = new ProccesorQueue(queueArray);
        Thread proccesorThread2 = new Thread(proccesorQueue2);
        proccesorThread2.setName("Обработчик 2");

        ProccesorQueue proccesorQueue3 = new ProccesorQueue(queueArray);
        Thread proccesorThread3 = new Thread(proccesorQueue3);
        proccesorThread3.setName("Обработчик 3");

        ProccesorQueue proccesorQueue4 = new ProccesorQueue(queueArray);
        Thread proccesorThread4 = new Thread(proccesorQueue4);
        proccesorThread4.setName("Обработчик 4");

        proccesorThread1.start();
        proccesorThread2.start();
        proccesorThread3.start();
        proccesorThread4.start();
*/



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
