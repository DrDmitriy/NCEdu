package ru.ncedu.dmdrozhzhin.moreThanHalfNonUnicFinder;

import java.util.*;

public class Generate {
    private static int digital = 0;

    public static Integer[] generateArray(int lenght) {
        Random random = new Random();
        int condtition = lenght / 2 + 1;
        Integer[] array = new Integer[lenght];
        for (int i = 0; i < lenght; i++) {
            if (i < condtition - 1) {
                array[i] = digital;
            } else {
                array[i] = random.nextInt(50);
            }
            //System.out.print("|" + array[i]);
        }

        List<Integer> list = new ArrayList(Arrays.asList(array));
        Collections.shuffle(list);
       /* System.out.println();
        for (Integer a : list){
            System.out.print(a + " ");
        }*/
        digital++;
        for (int i = 0; i < lenght; i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
