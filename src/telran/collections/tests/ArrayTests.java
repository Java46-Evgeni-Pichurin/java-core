package telran.collections.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

class ArrayTests {

    @Test
    void halfSum() {
        int[] arWithHalfSum = {40, -20, 50, 10, 20}; //sum = 100, 40 + 10 = 50
        int[] arWithNoHalfSum = {40, -20, 50, 5, 25}; //sum = 100 , no two numbers with sum = 50
        assertTrue(isHalfSumTwoNumbers(arWithHalfSum));
        assertFalse(isHalfSumTwoNumbers(arWithNoHalfSum));
    }

    private boolean isHalfSumTwoNumbers(int[] array) {
        int sum = IntStream.of(array).sum();
        int halfSum = sum / 2;
        if (sum % 2 == 1) {
            return false;
        }
        Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
        HashSet<Integer> hashSet = new HashSet<>(Arrays.asList(boxedArray));
        Map<Integer, Integer> pairs = new HashMap<>();
        for (int i : array) {
            int dif = halfSum - i;
            if (!pairs.containsKey(i) && hashSet.contains(dif) && dif != i) {
                pairs.put(dif, i);
            }
        }
        return !pairs.isEmpty();
    }

    @Test
    void valueWithMaxNegativeTest() {
        int[] arWithNegative = {10, 20000000, 2, 4, 40, -4, 10, -20000000, -2};
        int[] arWithNoNegative = {10, 20, 2, 4, 40, -14, 10, -21, -3};
        assertEquals(20000000, valueWithMaxNegative(arWithNegative));
        assertEquals(-1, valueWithMaxNegative(arWithNoNegative));
    }

    private Integer valueWithMaxNegative(int[] array) {
        int res = -1;
        Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
        HashSet<Integer> hashSet = new HashSet<>(Arrays.asList(boxedArray));
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int j : array) {
            if (hashSet.contains(j * -1)) {
                arrayList.add(j);
            }
        }
        for (Integer integer : arrayList) {
            if (integer > res) {
                res = integer;
            }
        }
        return res;
    }
}
