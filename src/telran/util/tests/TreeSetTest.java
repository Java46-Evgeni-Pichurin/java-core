package telran.util.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.TreeSet;

public class TreeSetTest extends SortedSetTests {
    TreeSet<Integer> tree;
	@Override
	protected Collection<Integer> createCollection() {
		
		return new TreeSet<Integer>();
	}
	int index = 0;
	@Override
	protected void orderLargeArray() {
		Integer tmp[] = new Integer[largeArray.length];
		index = 0;
		orderLargeArray(tmp, 0, largeArray.length - 1);
		largeArray = tmp;
	}
	private void orderLargeArray(Integer[] tmp, int left, int right) {
		if (left <= right) {
			int middle = (left + right) / 2;
			tmp[index++] = largeArray[middle];
			orderLargeArray(tmp, left, middle - 1);
			orderLargeArray(tmp, middle + 1, right);
		}
		
	}
	@Override
	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		tree = (TreeSet<Integer>)collection;
	}
	
	
	@Test
	void displayRotatedTest() {
		System.out.println("*".repeat(10));
		tree.displayRotated();
		System.out.println("*".repeat(10));
	}
	@Test
	void displayDirectoryTest() {
		System.out.println("*".repeat(10));
		tree.displayAsDirectory();
		System.out.println("*".repeat(10));
		/*
		   10
		     -5
		     13
		       20
		         15
		         40
		 */
	}
	@Test
	void heightTest() {
		assertEquals(4, tree.height());
	}
	@Test
	void widthTest() {
		assertEquals(3, tree.width());
	}
	@Test
	void inversionTest() {
		tree.inversion();
		Integer expected1[] = {40, 20, 15, 13, 10, -5};
		assertArrayEquals(expected1, tree.toArray(new Integer[0]));
		containsTest();
	}
	@Test
	void balanceTest() {
		Integer[] array = new Integer[63];
		fillArraySequence(array);
		collection = new TreeSet<>();
		tree = (TreeSet<Integer>)collection;
		fillCollection(array);
		assertEquals(63, tree.size());
		assertEquals(63, tree.height());
		assertEquals(1, tree.width());
		tree.balance();
		assertEquals(6, tree.height());
		assertEquals(32, tree.width());
		assertArrayEquals(array, tree.toArray(new Integer[0]));
		
	}

}
