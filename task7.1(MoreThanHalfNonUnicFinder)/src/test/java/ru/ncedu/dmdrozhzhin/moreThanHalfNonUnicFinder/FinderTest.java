package ru.ncedu.dmdrozhzhin.moreThanHalfNonUnicFinder;

import static org.junit.Assert.*;

public class FinderTest {

    @org.junit.Test
    public void findTest1() {
        Integer[] array = {2, 2, 2, 3, 3, 3, 3, 4, 2, 4, 2, 4, 2, 3, 2, 3, 2, 1, 2, 4, 2, 1, 2, 2, 2};
        assertEquals(new Finder().find(array), 2);
    }

    @org.junit.Test
    public void findTest2() {
        Integer[] array = Generate.generateArray(1000);
        assertEquals(new Finder().find(array), 0);
    }
}