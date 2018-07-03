package ru.ncedu.dmdrozhzhin.countBunaryUniys;

import org.junit.Test;

import static org.junit.Assert.*;

public class CountUnitsTest {
    CountUnits countUnits = new CountUnits();

    @Test
    public void countTest1() {
        assertArrayEquals(new int[]{1, 1, 2, 1, 2, 2, 3, 1, 2, 2}, countUnits.count(10));
    }

    @Test
    public void countTest2() {
        assertArrayEquals(new int[]{1}, countUnits.count(1));
    }

    @Test
    public void countTest3() {
        assertArrayEquals(null, countUnits.count(0));
    }
}