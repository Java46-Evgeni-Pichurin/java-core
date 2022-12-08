package telran.sumOf2dimArray;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class NumbersGroupTest {
    private static final int N_GROUPS = 10_000;
    private static final int N_NUMBERS_IN_GROUP = 10_000;
    private final int MAX_RANDOM_VALUE = 100_000;

    int[][] smallArray = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };

    @Test
    void functionalTest() {
        assertEquals(
                Arrays.stream(flattenArray(smallArray))
                        .sum(),
                new NumberGroups(smallArray).computeSum()
        );
    }



    @Test
    void performanceTest() {
        int[][] largeArray = getGroups(N_GROUPS, N_NUMBERS_IN_GROUP);
        NumberGroups numberGroups = new NumberGroups(largeArray);
        numberGroups.setnThreads(1);
        assertEquals(
                Arrays.stream(flattenArray(largeArray))
                        .asLongStream()
                        .sum(),
                numberGroups.computeSum()
        );

        numberGroups.setnThreads(5);
        assertEquals(
                Arrays.stream(flattenArray(largeArray))
                        .asLongStream()
                        .sum(),
                numberGroups.computeSum()
        );

        numberGroups.setnThreads(10);
        assertEquals(
                Arrays.stream(flattenArray(largeArray))
                        .asLongStream()
                        .sum(),
                numberGroups.computeSum()
        );

        numberGroups.setnThreads(20);
        assertEquals(
                Arrays.stream(flattenArray(largeArray))
                        .asLongStream()
                        .sum(),
                numberGroups.computeSum()
        );

        numberGroups.setnThreads(40);
        assertEquals(
                Arrays.stream(flattenArray(largeArray))
                        .asLongStream()
                        .sum(),
                numberGroups.computeSum()
        );


        numberGroups.setnThreads(100);
        assertEquals(
                Arrays.stream(flattenArray(largeArray))
                        .asLongStream()
                        .sum(),
                numberGroups.computeSum()
        );
    }

    private int[] flattenArray(int[][] arr) {
        return Arrays.stream(arr)
                .flatMapToInt(Arrays::stream)
                .toArray();
    }

    private int[][] getGroups(int nGroups, int nNumbersInGroup) {
        int[][] res = new int[nGroups][nNumbersInGroup];
        Arrays.stream(res).forEach(arr ->
                IntStream.range(0, arr.length)
                        .forEach(i -> arr[i] = new Random().nextInt(MAX_RANDOM_VALUE)));
        return res;
    }
}
