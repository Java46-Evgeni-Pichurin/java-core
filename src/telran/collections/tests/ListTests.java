package telran.collections.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class ListTests {
    Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    List<Integer> list = Arrays.asList(numbers);
    ArrayList<Integer> listMutable;

    @BeforeEach
    void setUp() {
        listMutable = new ArrayList<>(list);
    }

    @Test
    void removeRepeatedTest() {
        listMutable.addAll(Arrays.asList(numbers));
        removeRepeated(listMutable);
        System.out.println(listMutable.toString());
        assertArrayEquals(numbers, listMutable.toArray(Integer[]::new));
    }

    private void removeRepeated(List<Integer> list) {
        List<Integer> setList = list.stream().distinct().toList();
        list.clear();
        list.addAll(setList);
    }
}