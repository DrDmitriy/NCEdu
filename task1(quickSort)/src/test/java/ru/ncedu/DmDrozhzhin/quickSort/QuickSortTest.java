package ru.ncedu.dmdrozhzhin.quicksort;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @org.junit.jupiter.api.Test
    void comSort() {
        Integer[] integerArray = GenerateRandomArray.genIntegerRandomArr(100);
        Double[] doubleArray = GenerateRandomArray.genDoubleRandomArr(100);
        String[] stringArray = GenerateRandomArray.genStringRandomArr(100);

        QuickSort.comSort(integerArray);
        QuickSort.comSort(doubleArray);
        QuickSort.comSort(stringArray);

        assertEquals(CheckSort.isCorrectSort(integerArray),true);
        assertEquals(CheckSort.isCorrectSort(doubleArray),true);
        assertEquals(CheckSort.isCorrectSort(stringArray),true);
    }
}