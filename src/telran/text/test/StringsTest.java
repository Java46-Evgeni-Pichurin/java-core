package telran.text.test;

import static org.junit.jupiter.api.Assertions.*;
import static telran.text.Strings.sortStringsAsDates;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class StringsTest {
    @Test
    void testSortStringsAsDates() {
        String[] dates = {"10/08/2021", "30/12/2020", "5/08/2021"};
        String[] expected = {"30/12/2020", "5/08/2021", "10/08/2021"};
        assertArrayEquals(expected, sortStringsAsDates(dates));
    }
}