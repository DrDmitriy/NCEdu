package ru.ncedu.dmdrozhzhin.nonUnicFinder;


public class NonUnicFinder {
    private int arraySize;

    public NonUnicFinder() {
    }

    public int find(int[] array) {
        this.arraySize = array.length;

        for (int i = 0; i < this.arraySize; ++i) {
            if (array[i] >= this.arraySize) {
                if (array[array[i] - this.arraySize - i] > this.arraySize - 1) {
                    return array[i] - this.arraySize - i;
                }

                array[array[i] - this.arraySize - i] += this.arraySize + (array[i] - this.arraySize - i);
            } else {
                if (array[array[i]] > this.arraySize - 1) {
                    return array[i];
                }

                array[array[i]] += this.arraySize + array[i];
            }
        }

        return -1;
    }
}
