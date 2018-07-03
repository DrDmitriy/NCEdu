package ru.ncedu.dmdrozhzhin.nonUnicFinder;

public class Main {
      public static void main(String[] args) {
        int[] array = new int[]{7, 8, 4, 5, 9, 6, 1, 2, 6, 3};
        System.out.println((new NonUnicFinder()).find(array));
    }
}
