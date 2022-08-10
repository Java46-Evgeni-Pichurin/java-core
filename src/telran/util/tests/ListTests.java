package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.List;

abstract class ListTests extends CollectionTests {

	List<Integer> list;
	@BeforeEach
	@Override
	void setUp() throws Exception {
		super.setUp(); // content of the collection is {10, -5, 13, 20, 40, 15} from the setup 
		list = (List<Integer>)collection; //
	}

	@Test
	void addIndexTest() {
		
		assertTrue(list.add(0, 30));
		assertEquals(30, list.get(0));
		int size = list.size();
		for(int i = 0; i < N_NUMBERS; i++) {
			list.add(2, 100);
			
		}
		assertEquals(size + N_NUMBERS, list.size());
		assertEquals(100, list.get(2));
		assertEquals(100, list.get(2 + 100 - 1));
		size = list.size();
		assertTrue(list.add(size++, 1000));
		assertEquals(1000, list.get(size - 1));
		
	}
	
	@Test
	void removeIndexTest() {
		int size = list.size();
		assertEquals(10, list.remove(0));
		assertEquals(--size, list.size());
		assertEquals(-5, list.get(0));
		assertEquals(20, list.remove(2));
		assertEquals(--size, list.size());
		assertEquals(15, list.remove(size - 1));
		assertNull(list.remove(-1));
		assertNull(list.remove(size));
		
	}
	@Test
	void indexOfTest() {
		list.add(10);
		assertEquals(0, list.indexOf(10));
		assertEquals(-1, list.indexOf(-10));
	}
	@Test
	void lastIndexOfTest() {
		list.add(10);
		assertEquals(list.size() - 1, list.lastIndexOf(10));
		assertEquals(-1, list.lastIndexOf(-10));
	}

}
