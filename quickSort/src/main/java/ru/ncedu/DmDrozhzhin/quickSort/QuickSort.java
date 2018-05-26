package ru.ncedu.DmDrozhzhin.quickSort;
import java.util.Random;

public class QuickSort {

    public static Integer[] generateRandomArr(int arrayLenght){
        Random random = new Random();
        Integer[] array = new Integer[arrayLenght];

        for (int i = 0; i <array.length;i++){
            array[i] = random.nextInt(10000);
            System.out.print(array[i]+" ");
        }

        return array;
    }

    public static void qSort(Integer[] array, int first, int last){
        if (array.length==0) {return;}
        if (first>=last){return;}
        int i = first;
        int j = last;
        int mid = array[first+(last-first)/2];
        while (i<=j){
            while (array[i]<mid){i++;}
            while (array[j]>mid){j--;}

            if (i<=j) {
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                i++;
                j--;
            }
        }
        if(first<j){
            qSort(array,first,j);
        }
        if(last>i) {
            qSort(array, i, last);
        }
    }

    public static boolean isCorrectSort(Integer[] arr){
        try {
            for (int i = 0; i < arr.length-1; i++) {
                if (arr[i] > arr[i + 1]) {
                    return false;
                }
            }
            return true;
        }
        catch (NullPointerException e){
            System.out.println("isCorrectSort return "+e.toString());
          return false;
        }
    }



}
