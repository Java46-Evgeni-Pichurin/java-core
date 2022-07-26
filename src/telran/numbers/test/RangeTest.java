package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.numbers.EvenNumbersPredicate;
import telran.numbers.OddNumbersPredicate;
import telran.numbers.Range;

import java.util.Arrays;

class RangeTest {
    Range range = new Range(-2, 5);

    @BeforeEach
    void setUp() throws Exception {
        range = new Range(-2, 5);
    }

    @Test
    void lengthTest() {
        assertEquals(8, range.length());
    }

    @Test
    void iterableNoPredicateTest() {
        int[] expected = {
                -2, -1, 0, 1, 2, 3, 4, 5
        };
        int[] actual = getActualArray(8);
        assertArrayEquals(expected, actual);
    }

    @Test
    void iterablePredicateTest_1() {
        int[] expected = {-2, 0, 2, 4};
        range.setPredicate(new EvenNumbersPredicate());
        int[] actual = getActualArray(4);
        assertArrayEquals(expected, actual);
    }
    @Test
    void iterablePredicateTest_2() {
        int[] expected = {-1, 1, 3, 5};
        range.setPredicate(new OddNumbersPredicate());
        int[] actual = getActualArray(4);
        assertArrayEquals(expected, actual);
    }

    private int[] getActualArray(int size) {
        int[] res = new int[size];
        int ind = 0;
        for (int num : range) {
            res[ind++] = num;
        }
        return res;
    }
}