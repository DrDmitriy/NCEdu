package ru.ncedu.dmdrozhzhin.moreThanHalfNonUnicFinder;

public class Finder {
    public int find(Integer[] array) {
        int interator = 0;
        int count = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[interator] == array[i]) {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                interator = i;
                count = 0;
            }
        }
        return array[interator];
    }
}
