package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static telran.recursion.LineRecursion.*
;class RecursionTest {
int count = 0;
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void factorialTest() {
		assertEquals(24, factorial(4));
	}
	@Test
	void powTest() {
		assertEquals(1000, pow(10, 3));
		assertEquals(-1000, pow(-10, 3));
		assertEquals(10000,pow(-10, 4));
		assertEquals(1,pow(-10, 0));
		assertEquals(243,pow(3, 5));
		assertEquals(-10,pow(-10, 1));
		assertEquals(944076141,pow(981, 3));
	}
	@Test
	void sumTest() {
		int[] ar = {1, 2, 3, 4};
		assertEquals(10, sum(ar));
	}
	@Test
	void squareTest() {
		assertEquals(4, square(2));
		assertEquals(16, square(4));
		assertEquals(1, square(1));
		assertEquals(0, square(0));
		assertEquals(100, square(-10));
	}

}
