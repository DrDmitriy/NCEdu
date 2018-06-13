package ru.ncedu.DmDrozhzhin.quickSort;

import java.util.Random;

public class GenerateRandomArray {
    public static Integer[] genIntegerRandomArr(int arrayLenght) {
        Random random = new Random();
        Integer[] array = new Integer[arrayLenght];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10000);
        }
        return array;
    }

    public static Double[] genDoubleRandomArr(int arrayLenght) {
        Random random = new Random();
        Double[] array = new Double[arrayLenght];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextDouble();
        }
        return array;
    }

    public static String[] genStringRandomArr(int arrayLenght) {
        Random random = new Random();
        String[] array = new String[arrayLenght];

        for (int i = 0; i < array.length; i++) {
            array[i] = String.valueOf((char) (random.nextInt(26) + 'а'));
        }
        return array;
    }
}
