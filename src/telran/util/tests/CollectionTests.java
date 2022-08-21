package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

abstract class CollectionTests {
	protected static final int N_NUMBERS = 10000;
	protected static final int N_RANDOM_NUMBERS = 100;
	private static final int N_RUNS = 10000;
	private static final int N_RANDOM_RUNS = 10;
	protected Collection<Integer> collection;

	protected abstract Collection<Integer> createCollection();

	Integer expected[] = { 10, -5, 13, 20, 40, 15 };

	@BeforeEach
	void setUp() throws Exception {
		collection = createCollection();
		fillCollection();

	}

	private void fillCollection() {
		for (Integer num : expected) {
			collection.add(num);
		}

	}

	@Test
	void addTest() {
		assertTrue(collection.add(100)); // adding not existing number
		assertTrue(collection.add(10)); // adding existing number
		int size = collection.size();
		for (int i = 0; i < N_NUMBERS; i++) {
			collection.add(100);
		}
		assertEquals(size + N_NUMBERS, collection.size());
	}

	@Test
	void removeTest() {
		int size = collection.size();
		assertTrue(collection.remove(expected[0]));
		assertEquals(--size, collection.size());
		assertFalse(collection.remove(expected[0]));
		assertEquals(size, collection.size());
	}

	@Test
	void removeIfTest() {
		Predicate<Integer> allFalsePredicate = new AllFalsePredicate();
		// Nothing removed test
		assertFalse(collection.removeIf(allFalsePredicate));
		assertEquals(expected.length, collection.size());
		/************************************************************/
		// even numbers removed test
		for (int i = 0; i < N_RANDOM_RUNS; i++) {
			fillRandomCollection();
			collection.removeIf(new EvenNumbersPredicate());
			for (int num : collection) {
				assertTrue(num % 2 == 1);
			}
		}
		/**************************************************************/
		// All removed test
		assertTrue(collection.removeIf(allFalsePredicate.negate()));
		assertEquals(0, collection.size());
	}

	private void fillRandomCollection() {
		collection = createCollection();
		for (int i = 0; i < N_RANDOM_NUMBERS; i++) {
			collection.add((int) (Math.random() * Integer.MAX_VALUE));
		}

	}

	@Test
	void containsTest() {
		assertTrue(collection.contains(10));
		assertFalse(collection.contains(1000));
	}

	@Test
	void toArrayTest() {
		assertArrayEquals(expected, collection.toArray(new Integer[0]));
		assertTrue(expected == collection.toArray(expected));
		Integer expected2[] = new Integer[100];
		assertTrue(expected2 == collection.toArray(expected2));
		assertArrayEquals(expected, Arrays.copyOf(expected2, collection.size()));
		for (int i = collection.size(); i < expected2.length; i++) {
			assertNull(expected2[i]);
		}

	}

	@Test
	void sizeTest() {
		assertEquals(expected.length, collection.size());
	}

	@Test
	void wrongIteratorRemoveTest() {
		Iterator<Integer> it = collection.iterator();
		wrongRemove(it); // first remove
		it.next();
		it.next();
		it.remove(); // two removes with no next
		wrongRemove(it);
	}

	//@Test
	void removeIfPerformanceTest() {
		Predicate<Integer> predicate = new AllFalsePredicate().negate();
		for (int i = 0; i < N_RUNS; i++) {
			fillLargeCollection();
			collection.removeIf(predicate);
		}
	}

	private void fillLargeCollection() {
		for (int i = 0; i < N_NUMBERS; i++) {
			collection.add((int)(Math.random()*Integer.MAX_VALUE));
		}

	}

	protected void wrongRemove(Iterator<Integer> it) {
		boolean flException = false;
		try {
			it.remove();
		} catch (IllegalStateException e) {
			flException = true;
		}
		assertTrue(flException);
	}
	@Test
	void emptyCollectionTest() {
		collection = createCollection();
		assertArrayEquals(new Integer[0], collection.toArray(new Integer[0]));
	}

}
