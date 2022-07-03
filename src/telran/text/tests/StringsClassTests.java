package telran.text.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.text.Strings;

class StringsClassTests {

	@Test
	void deepCompareTest() {
		String str1 = "123,tT89";
		String str2 = "123,tT89";
		String str3 = "19";
		String str5 = "98Tt,321";
		String str4 ="kugy";
		int expected1[] = {8, 0};
		int expected2[] = {1, 1};
		int expected3[] = {0, 8};
		int expected4[] = {0, 0};
		assertArrayEquals(expected1, Strings.deepNoRepeatedCompare(str1, str2));
		assertArrayEquals(expected2, Strings.deepNoRepeatedCompare(str1, str3));
		assertArrayEquals(expected3, Strings.deepNoRepeatedCompare(str1, str5));
		assertArrayEquals(expected4, Strings.deepNoRepeatedCompare(str1, str4));
	}
	
	@Test
	void isAnagramTests() {
		String str1 = "hello";
		String str2 = "Hello";
		String str3 = "eloHl";
		String str4 = "Helo";
		String str5 = "hhhhhqqqy";
		String str6 = "qhhqqhhhi";
		assertTrue(Strings.isAnagram(str1, str2));
		assertTrue(Strings.isAnagram(str1, str3));
		assertFalse(Strings.isAnagram(str1, str4));
		assertTrue(Strings.isAnagram(str1, str1));
		assertFalse(Strings.isAnagram(str1, str5));
		assertFalse(Strings.isAnagram(str6, str5));
	}

}
