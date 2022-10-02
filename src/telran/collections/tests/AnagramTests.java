package telran.collections.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.util.Anagram.isAnagram;

import org.junit.jupiter.api.Test;

class AnagramTests {
	String word = "yellow";
	@Test
	void testAnagramTrue() {
		assertTrue(isAnagram(word, "loweyl"));
		assertTrue(isAnagram(word, "elolyw"));
		assertTrue(isAnagram(word, "wolley"));
		assertTrue(isAnagram(word, "loleyw"));
		
	}
	@Test
	void testAnagramFalse() {
		assertFalse(isAnagram(word,""));
		assertFalse(isAnagram(word, "yellob"));
		assertFalse(isAnagram(word,"yello"));
		assertFalse(isAnagram(word,"yelllo"));
		


		
	}


}
