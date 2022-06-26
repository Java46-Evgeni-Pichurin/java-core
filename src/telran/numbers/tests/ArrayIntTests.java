package telran.numbers.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.numbers.ArrayInt;

class ArrayIntTests {

	private static final int SMALL_N_NUMBERS = 100;
	private static final int LARGE_N_NUMBERS = 1_0000;
	private static final int N_RUNS = 10000;
	@Test
	void referenceTests() {
		int ar[] = {1 ,2 ,3, 4};
		int ar1[] = ar;
		ar1[1] = 100;
		assertEquals(100, ar[1]);
	}
	@Test
	void addNumberTest() {
		int expected[] = {1, 2, -10, 20};
		int ar[] = {1, 2, -10};
		//assertTrue(expected == ArrayInt.addNumber(ar, 20)); - wrong test as the references will be different
		assertArrayEquals(expected, ArrayInt.addNumber(ar, 20));
	}
	@Test
	void insertNumberTest() {
		int expected[] = {1, 2, -10, 20};
		int ar[] = {1, 2, 20};
		assertArrayEquals(expected, ArrayInt.insertNumber(ar, 2, -10));
	}
	@Test
	void removeNumberTest() {
		int expected1[] = {1, 20};
		int expected2[] = {2, 20};
		int expected3[] = {1, 2};
		int ar[] = {1, 2, 20};
		assertArrayEquals(expected1, ArrayInt.removeNumber(ar, 1));
		assertArrayEquals(expected2, ArrayInt.removeNumber(ar, 0));
		assertArrayEquals(expected3, ArrayInt.removeNumber(ar, 2));
	}

	@Test
	void indexOfTest() {
		int ar[] = {1, 2, 20, 2};
		assertEquals(1, ArrayInt.indexOf(ar, 2));
		assertEquals(0, ArrayInt.indexOf(ar, 1));
		assertEquals(-1, ArrayInt.indexOf(ar, 10));
	}
	@Test
	void sortSmallTest() {
		int ar[] = new int[SMALL_N_NUMBERS];
		fillRandomNumbers(ar);
		ArrayInt.sort(ar);
		runSortTest(ar);
	}
	@Test
	void sortLargeTest() {
		int ar[] = new int[LARGE_N_NUMBERS];
		fillRandomNumbers(ar);
		Arrays.sort(ar); //sorting with O[N*LogN]
		ArrayInt.sort(ar);// bubble sorting with optimization of the HW #4
		runSortTest(ar);
	}
	@Test
	void binarySearchTest() {
		int ar[] = {1, 5, 100, 200, 350, 500};
		assertEquals(0, ArrayInt.binaryIndexOf(ar, 1));
		assertEquals(2, ArrayInt.binaryIndexOf(ar, 100));
		assertEquals(5, ArrayInt.binaryIndexOf(ar, 500));
		assertTrue(ArrayInt.binaryIndexOf(ar, 150) < 0);
		//Tests for HW #4
		assertEquals(-2, ArrayInt.binaryIndexOf(ar, 4));
		assertEquals(-1, ArrayInt.binaryIndexOf(ar, -10));
		assertEquals(-4, ArrayInt.binaryIndexOf(ar, 150));
		assertEquals(-7, ArrayInt.binaryIndexOf(ar, 600));
		int ar1[] = {1,1,1,1,1};
		assertEquals(0, ArrayInt.binaryIndexOf(ar1, 1));


	}
	@Test
	void indexOfPerformance() {
		int ar[] = new int[LARGE_N_NUMBERS];
		for(int i = 0; i < N_RUNS; i++) {
			assertTrue(ArrayInt.indexOf(ar, 1) < 0);
		}
	}
	@Test
	void binaryIndexOfPerformance() {
		int ar[] = new int[LARGE_N_NUMBERS];
		for(int i = 0; i < N_RUNS; i++) {
			assertTrue(ArrayInt.binaryIndexOf(ar, 1) < 0);
		}
	}


	private void runSortTest(int[] ar) {
		for (int i = 1; i < ar.length; i++) {
			assertTrue(ar[i - 1] <= ar[i]);
		}

	}
	private void fillRandomNumbers(int[] ar) {
		for (int i = 0; i < ar.length; i++) {
			ar[i] = (int) (Math.random() * Integer.MAX_VALUE);
		}

	}

}