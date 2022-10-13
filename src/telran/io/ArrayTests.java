package telran.io;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class ArrayTests {
    static final int POSITIVE_VAL = 1;
    static final int NEGATIVE_VAL = -1;
    static final int ZERO_VAL = 0;

    // O[6*N] ~ O[N]
    <T> boolean isOneSwapForSorted(T[] array) {
        boolean res = false;
        T[] copy = Arrays.copyOf(array, array.length);
        int[] helper = new int[array.length - 1];
        fillHelper(helper, array);
        Map<Object, Long> map = Arrays.stream(helper)
                .boxed()
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        if (checkMap(map, helper)) {
            int extraordinary = map.getOrDefault(NEGATIVE_VAL, 0L) <= 2 ? NEGATIVE_VAL : POSITIVE_VAL;
            boolean isNeighbor = map.getOrDefault(NEGATIVE_VAL, 0L) == 1 ||
                    map.getOrDefault(POSITIVE_VAL, 0L) == 1;
            res = checkArray(helper, copy, extraordinary, isNeighbor);
        }
        return res;
    }

    private <T> void fillHelper(int[] helper, T[] array) {
        int compRes;
        for (int i = 0; i < array.length - 1; i++) {
            @SuppressWarnings("unchecked")
            Comparable<T> comparable = (Comparable<T>) array[i];
            compRes = comparable.compareTo(array[i + 1]);
            if (compRes > 0) {
                helper[i] = POSITIVE_VAL;
            } else if (compRes < 0) {
                helper[i] = NEGATIVE_VAL;
            } else {
                helper[i] = ZERO_VAL;
            }
        }
    }

    private boolean checkMap(Map<Object, Long> map, int[] helper) {
        Long zeroKey = map.getOrDefault(ZERO_VAL, 0L);
        Long negativeKey = map.getOrDefault(NEGATIVE_VAL, 0L);
        Long positiveKey = map.getOrDefault(POSITIVE_VAL, 0L);
        return negativeKey != 0 &&
                positiveKey != 0 &&
                zeroKey != helper.length &&
                (negativeKey <= 2 || positiveKey <= 2) &&
                !negativeKey.equals(positiveKey);
    }

    private <T> boolean checkArray(int[] helper, T[] arrayCopy, int extraordinary, boolean isNeighbor) {
        int firstIndex = -1;
        int lastIndex = -1;
        for (int i = 0; i < helper.length; i++) {
            if (firstIndex >= 0 && lastIndex >= 0) {
                break;
            }
            if (helper[i] == extraordinary) {
                if (firstIndex < 0) {
                    firstIndex = i;
                    if (isNeighbor) {
                        lastIndex = i + 1;
                        break;
                    }
                } else {
                    lastIndex = i + 1;
                }
            }
        }
        swap(arrayCopy, firstIndex, lastIndex);
        return checkFixedArr(arrayCopy, extraordinary, helper);
    }

    private <T> void swap(T[] arrayCopy, int firstIndex, int lastIndex) {
        T tmp = arrayCopy[firstIndex];
        arrayCopy[firstIndex] = arrayCopy[lastIndex];
        arrayCopy[lastIndex] = tmp;
    }

    private <T> boolean checkFixedArr(T[] arrayCopy, int extraordinary, int[] helper) {
        fillHelper(helper, arrayCopy);
        return Arrays.stream(helper).allMatch(x -> x == -extraordinary || x == ZERO_VAL);
    }

    /********************************************************************************************************************/

    Integer[] arTrue1 = {1, 6, 3, 4, 2, 10};
    Integer[] arTrue2 = {1, 2, 4, 3, 6, 10};
    Integer[] arTrue3 = {10, 2, 3, 4, 6, 1};
    Integer[] arTrue4 = {3, 2, 3, 4, 6, 10};
    Integer[] arTrue5 = {1, 2, 3, 4, 6, 10, 7};
    Integer[] arTrue6 = {11, 5, 9, 6, 10, 1};

    Integer[] arFalse1 = {1, 10, 2, 3, 6, 4};
    Integer[] arFalse2 = {1, 2, 4, 2, 10, 6};
    Integer[] arFalse3 = {1, 2, 3, 4, 6, 10};

    @Test
    void isOneSwapTest() {
        assertTrue(isOneSwapForSorted(arTrue1));
        assertTrue(isOneSwapForSorted(arTrue2));
        assertTrue(isOneSwapForSorted(arTrue3));
        assertTrue(isOneSwapForSorted(arTrue4));
        assertTrue(isOneSwapForSorted(arTrue5));
        assertTrue(isOneSwapForSorted(arTrue6));
    }

    @Test
    void isNotOneSwapTest() {
        assertFalse(isOneSwapForSorted(arFalse1));
        assertFalse(isOneSwapForSorted(arFalse2));
        assertFalse(isOneSwapForSorted(arFalse3));
    }
}