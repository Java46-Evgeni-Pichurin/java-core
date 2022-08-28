package telran.oop.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.oop.numbers.*;

class RangeTest {
	Range range = new Range(1,3);

	@BeforeEach
	void setUp() throws Exception {
		range = new Range(1, 3);
	}

	@Test
	void lengthTest() {
		assertEquals(3, range.length());
	}
	@Test
	void iterableNoPredicateTest() {
		int expected[] = {
				1, 2, 3
		};
		int actual[] = getActualArray(3);
		assertArrayEquals(expected, actual);
	}
	@Test
	void iterablePredicateTest() {
		//Test for HW #12
		int expected[] = {2};
		Predicate<Integer> evenPredicate = n -> n % 2 == 0;
		range.setPredicate(evenPredicate);
		int actual[] = getActualArray(1);
		assertArrayEquals(expected, actual);
		int expectedOdd[] = {1, 3};
		range.setPredicate(evenPredicate.negate());
		actual = getActualArray(2);
		assertArrayEquals(expectedOdd, actual);
	}

	private int[] getActualArray(int size) {
		int [] res = new int[size];
		int ind = 0;
		for(int num: range) {
			res[ind++] = num;
		}
		return res;
	}

}
