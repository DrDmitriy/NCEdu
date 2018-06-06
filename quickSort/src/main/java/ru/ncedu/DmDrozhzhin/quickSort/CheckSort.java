package ru.ncedu.DmDrozhzhin.quickSort;

public class CheckSort {
    public static <T extends Comparable> boolean isCorrectSort(T[] arr) {
        try {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    return false;
                }
            }
            return true;
        } catch (NullPointerException e) {
            System.out.println("isCorrectSort return " + e.toString());
            return false;
        }
    }
}
