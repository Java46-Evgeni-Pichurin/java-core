package telran.text.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.text.Strings.matches;
import static telran.text.Strings.sortStringsAsNumbers;

import org.junit.jupiter.api.Test;

import telran.text.Strings;

class StringsClassTests {

    @Test
    void matchesTest() {
        String[][] data = {
                {"David", "david", "match"},
                {"John F", "John Fitzgerald", "match"},
                {"John K", "John Fitzgerald", "no match"},
                {"Anna Maria Magdalena", "Anna Magdalena", "match"},
                {"Anna Maria Magdalena", "Maria Magdalena", "match"},
                {"Anna Maria Magdalena", "Anna Maria Roberta", "no match"},
                {"Anna Maria Magdalena", "Anna Magdalena Roberta", "no match"},
                {"Anna Maria Magdalena", "Anna Magdalena Maria", "no match"}
        };
        for (int i = 0; i < data.length; i++) {
            assertEquals(data[i][2], matches(data[i][0], data[i][1]));
        }
    }

    @Test
    void sortStringsAsNumbersTest() {
        String[][] data = {
                {"0", "999", "11", "145", "1"},
                {"0", "0", "0", "0"},
                {"100", "50", "25", "10"},
                {"0", "01", "011", "0111"}
        };
        for (int i = 0; i < data.length; i++) {
            sortStringsAsNumbers(data[i]);
        }
    }
}
