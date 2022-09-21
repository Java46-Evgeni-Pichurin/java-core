package telran.collections.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.Words;

class WordsTest {
    Words words = new Words();

    @BeforeEach
    void setUp() {
        words = new Words();
    }

    @Test
    void testAddWord() {
        assertTrue(words.addWord("Abc"));
        assertFalse(words.addWord("Abc"));
        assertFalse(words.addWord("ABC"));
        assertTrue(words.addWord("aaa"));
    }

    @Test
    void testGetWordsByPrefix() {
        String[] expected1 = {};
        assertArrayEquals(expected1, words.getWordsByPrefix("").toArray(String[]::new));
        words.addWord("Israel");
        words.addWord("Island");
        words.addWord("Ireland");
        words.addWord("India");
        String[] expected2 = {"Israel", "Island", "Ireland", "India"};
        assertArrayEquals(expected2, words.getWordsByPrefix("i").toArray(String[]::new));
        String[] expected3 = {"Israel", "Island"};
        assertArrayEquals(expected3, words.getWordsByPrefix("IS").toArray(String[]::new));
        String[] expected4 = {"Israel"};
        assertArrayEquals(expected4, words.getWordsByPrefix("IsR").toArray(String[]::new));
    }
}