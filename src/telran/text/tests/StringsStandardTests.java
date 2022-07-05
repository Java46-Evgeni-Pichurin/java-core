package telran.text.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;

class StringsStandardTests {

	@Test
	void stringArraysTest() throws UnsupportedEncodingException {
		String strEnglish = "hello";
		String strHebrew = "ωμεν";
		char arCh[] = {'h', 'e', 'l','l','o'};
		byte arBytes[] = {'h', 'e', 'l','l','o'};
		assertArrayEquals(arCh, strEnglish.toCharArray());
		assertArrayEquals(arBytes, strEnglish.getBytes());
		assertEquals(5, strEnglish.getBytes().length);
		assertEquals(4, strHebrew.toCharArray().length);
		assertEquals(8, strHebrew.getBytes("utf-8").length);
		
		
	}
	@Test
	void equalsStrings() {
		String str1 = "Hello";//Java creates new array of char's and str1 is reference to the array
		String str2 = "Hello";//Java recognizes array of char's existence and puts reference to that array
		String str3 = new String("Hello");//Java creates new array and fills it with existed array of char's
		assertTrue(str1 == str2);
		assertFalse(str1 == str3);
		assertTrue(str1.equals(str3));
		assertEquals(str1, str3);
	}
	@Test
	void comparingStrings() {
		String str1 = "123456789";
		String str2 = "ab";
		String str3 = "Ab";
		String str4 = "aB";
		assertTrue(str1.compareTo(str2) < 0);
		assertEquals('1' - 'a', str1.compareTo(str2));
		assertTrue(str2.compareTo(str3) > 0);
		assertEquals(0, str2.compareToIgnoreCase(str4));
		assertEquals(0, str2.compareTo(str4.toLowerCase()));
		
	}
	@Test
	void indexOfTests() {
		String str = "Hello";
		assertEquals(2, str.indexOf('l'));
		assertEquals(3, str.lastIndexOf('l'));
		assertEquals(-1, str.indexOf('w'));
	}
	@Test
	void formatTest() {
		String strExpected = "a=5";
		int b = 10;
		assertEquals(strExpected, String.format("a=%d", b / 2));
	}
	@Test
	void replaceTest() {
		String str = "Hello";
		//str = str.concat(" Vasya");
		str = str.replace("ello", "allo");
		assertEquals("Hallo", str);
		StringBuilder strBuilder = new StringBuilder("Hello");
		strBuilder.replace(1, 5, "allo");
		assertEquals("Hallo", strBuilder.toString());
	}

}
