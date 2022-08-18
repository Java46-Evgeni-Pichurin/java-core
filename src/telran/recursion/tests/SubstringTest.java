package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.recursion.Substring.*;

import org.junit.jupiter.api.Test;

public class SubstringTest {
    String str1 = "communicate";
    String str2 = "boboobooob";
    String str3 = "4rrrr4rr";

    @Test
    void hasSubstringTest() {
        assertTrue(isSubstring(str1, str1));
        assertTrue(isSubstring(str1, ""));
        assertTrue(isSubstring(str1, "ca"));
        assertTrue(isSubstring(str1, "mu"));
        assertTrue(isSubstring(str1, "unic"));
        assertTrue(isSubstring(str1, "ommunicate"));
        assertTrue(isSubstring(str1, "communicat"));
        assertTrue(isSubstring(str2, "obooob"));
        assertTrue(isSubstring(str3, "rrr"));
        assertTrue(isSubstring(str3, "rrrr4rr"));
    }

    @Test
    void noSubstringTest() {
        assertFalse(isSubstring(str1, "Communicate"));
        assertFalse(isSubstring(str1, "communicatee"));
        assertFalse(isSubstring(str1, "communicatee"));
        assertFalse(isSubstring(str1, "comunicate"));
        assertFalse(isSubstring(str1, "commm"));
        assertFalse(isSubstring(str1, " communicat"));
        assertFalse(isSubstring(str2, "bobooob"));
        assertFalse(isSubstring(str3, "rrrr4rrr"));
    }
}
