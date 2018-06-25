package ru.ncedu.dmdrozhzhin.queueSortThread;

import ru.ncedu.dmdrozhzhin.quicksort.GenerateRandomArray;
import ru.ncedu.dmdrozhzhin.quicksort.QuickSort;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static  void main(String[] args) {
       Integer[] integerAraay = GenerateRandomArray.genIntegerRandomArr(10);
       QuickSort.comSort(integerAraay);
       QueueArray queueArray = new QueueArray();
       FillingQueue fillingQueue = new FillingQueue(queueArray);
       while (true){
           fillingQueue.addArray();
       }



    }
}
