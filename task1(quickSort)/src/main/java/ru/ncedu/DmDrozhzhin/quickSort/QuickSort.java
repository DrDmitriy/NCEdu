package ru.ncedu.dmdrozhzhin.quicksort;

public class QuickSort {
    public static <T extends Comparable> void comSort(T[] array) {
        int first = 0;
        int last = array.length - 1;
        comSort(array, first, last);
    }

    public static <T extends Comparable> void comSort(T[] array, int first, int last) {
        if (array.length == 0) {
            return;
        }
        if (first >= last) {
            return;
        }
        int i = first;
        int j = last;
        T mid = array[first + (last - first) / 2];
        while (i <= j) {
            while (array[i].compareTo(mid) < 0) {
                i++;
            }
            while (array[j].compareTo(mid) > 0) {
                j--;
            }
            if (i <= j) {
                T temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                i++;
                j--;
            }
        }
        if (first < j) {
            comSort(array, first, j);
        }
        if (last > i) {
            comSort(array, i, last);
        }
    }
}