package telran.text.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.text.Strings.matches;
import static telran.text.Strings.sortStringsAsNumbers;

import org.junit.jupiter.api.Test;

import telran.text.Strings;

class StringsClassTests {

    private static final int N_RUNS = 10000;
    private static final int N_STRINGS = 10000;

    @Test
    void matchesTest() {
        String str1Test1 = "David";
        String str2Test1 = "david";

        String str1Test2 = "John F";
        String str2Test2 = "John Fitzgerald";

        String str1Test3 = "John K";
        String str2Test3 = "John Fitzgerald";

        String str1Test4 = "Anna Maria Magdalena";
        String str2Test4 = "Anna Magdalena";

        String str1Test5 = str1Test4;
        String str2Test5 = "Maria Magdalena";

        String str1Test6 = str1Test4;
        String str2Test6 = "Anna Maria Roberta";

        String str1Test7 = str1Test4;
        String str2Test7 = "Anna Magdalena Roberta";

        String str1Test8 = str1Test4;
        String str2Test8 = "Anna Magdalena Maria";

        String expectedMatch = "match";
        String expectedNoMatch = "no match";

        assertEquals(expectedMatch, matches(str1Test1, str2Test1));
        assertEquals(expectedMatch, matches(str1Test2, str2Test2));
        assertEquals(expectedNoMatch, matches(str1Test3, str2Test3));
        assertEquals(expectedMatch, matches(str1Test4, str2Test4));
        assertEquals(expectedMatch, matches(str1Test5, str2Test5));
        assertEquals(expectedNoMatch, matches(str1Test6, str2Test6));
        assertEquals(expectedNoMatch, matches(str1Test7, str2Test7));
        assertEquals(expectedNoMatch, matches(str1Test8, str2Test8));
    }

    @Test
    void sortStringsAsNumbersTest() {
        String[] strArr1 = {"0", "999", "11", "145", "1"};
        String[] strArr2 = {"0", "0", "0", "0"};
        String[] strArr3 = {"100", "50", "25", "10"};
        String[] strArr4 = {"0", "01", "011", "0111"};
        sortStringsAsNumbers(strArr1);
        sortStringsAsNumbers(strArr2);
        sortStringsAsNumbers(strArr3);
        sortStringsAsNumbers(strArr4);
    }
}
