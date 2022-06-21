package telran.numbers.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.numbers.ArrayInt;

class ArrayIntTests {

	@Test
	void referenceTests() {
		int[] ar = {1 ,2 ,3, 4};
		int[] ar1 = ar;
		ar1[1] = 100;
		assertEquals(100, ar[1]);
	}
	@Test
	void addNumberTest() {
		int[] expected = {1, 2, -10, 20};
		int[] ar = {1, 2, -10};
		//assertTrue(expected == ArrayInt.addNumber(ar, 20)); - wrong test as the references will be different
		assertArrayEquals(expected, ArrayInt.addNumber(ar, 20));
	}
	@Test
	void insertNumberTest() {
		int[] expected = {1, 2, -10, 20};
		int[] ar = {1, 2, 20};
		assertArrayEquals(expected, ArrayInt.insertNumber(ar, 2, -10));
	}
	@Test
	void removeNumberTest() {
		int[] expected1 = {1, 20};
		int[] expected2 = {2, 20};
		int[] expected3 = {1, 2};
		int[] ar = {1, 2, 20};
		assertArrayEquals(expected1, ArrayInt.removeNumber(ar, 1));
		assertArrayEquals(expected2, ArrayInt.removeNumber(ar, 0));
		assertArrayEquals(expected3, ArrayInt.removeNumber(ar, 2));
	}
	
	@Test
	void indexOfTest() {
		int[] ar = {1, 2, 20, 2};
		assertEquals(1, ArrayInt.indexOf(ar, 2));
		assertEquals(0, ArrayInt.indexOf(ar, 1));
		assertEquals(-1, ArrayInt.indexOf(ar, 10));
	}

}
