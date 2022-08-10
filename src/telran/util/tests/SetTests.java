package telran.util.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.Collection;

import java.util.Arrays;

public abstract class SetTests extends CollectionTests {

	@Test
	@Override
	void addTest() {
		assertTrue(collection.add(100)); // adding not existing number
		assertFalse(collection.add(10)); // adding existing number
		int size = collection.size();
		for (int i = 0; i < N_NUMBERS; i++) {
			collection.add(101 + i);
		}
		assertEquals(size + N_NUMBERS, collection.size());
	}

	@Test
	@Override
	void toArrayTest() {
		Integer[] expected1 = { 10, -5, 13, 20, 40, 15 };
		Integer[] res = collection.toArray(new Integer[0]);

		Arrays.sort(expected1);
		Arrays.sort(res);

		assertArrayEquals(expected1, res);
		assertTrue(expected1 == collection.toArray(expected1));
		Integer expected2[] = new Integer[100];
		assertTrue(expected2 == collection.toArray(expected2));
		assertArrayEquals(expected1, Arrays.copyOf(expected2, res.length));
		for (int i = res.length; i < expected2.length; i++) {
			assertNull(expected2[i]);
		}
	}

}
