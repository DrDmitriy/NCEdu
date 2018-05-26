package ru.ncedu.DmDrozhzhin.quickSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        Integer[] array = QuickSort.generateRandomArr(100);
        int first = 0;
        int last = array.length-1;

        QuickSort.qSort(array,first,last);
        System.out.println("");
        System.out.println(Arrays.toString(array));
        System.out.println(QuickSort.isCorrectSort(array));
    }

}
