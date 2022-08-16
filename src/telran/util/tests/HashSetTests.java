package telran.util.tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.HashSet;

public class HashSetTests extends SetTests {

	@Override
	protected Collection<Integer> createCollection() {
		
		return new HashSet<>();
	}
	@Test
	@Override
	void toArrayTest() {
		Arrays.sort(expected);
		Integer actual[] = collection.toArray(new Integer[0]);
		Arrays.sort(actual);
		assertArrayEquals(expected, actual);
		assertTrue(expected == collection.toArray(expected));
		Arrays.sort(expected);
		Integer expected2[] = new Integer[100];
		assertTrue(expected2 == collection.toArray(expected2));
		Integer actual2[] = Arrays.copyOf(expected2, collection.size());
		Arrays.sort(actual2);
		assertArrayEquals(expected, actual2);
		for (int i = collection.size(); i < expected2.length; i++) {
			assertNull(expected2[i]);
		}
	}

}
