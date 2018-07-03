package ru.ncedu.dmdrozhzhin.countBunaryUniys;

public class CountUnits {
    public int[] count(int arrayLenght) {
        if (arrayLenght == 0) {
            System.out.println("lenght is 0");
            return null;
        }
        int[] array = genrateArray(arrayLenght);

        int count;
        for (int i = 1; i < array.length; i++) {
            count = 0;
            count += array[(array[i] / 2) - 1];
            if ((array[i]) % 2 == 1) {
                count++;
            }
            array[i] = count;
        }
        return array;
    }

    private int[] genrateArray(int lenght) {
        int array[] = new int[lenght];

        for (int i = 0; i < lenght; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    public static void main(String[] args) {
        CountUnits countUnits = new CountUnits();

        countUnits.count(0);
    }
}
