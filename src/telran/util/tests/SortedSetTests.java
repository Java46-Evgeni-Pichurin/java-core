package telran.util.tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.SortedSet;
//Integer expected[] = { 10, -5, 13, 20, 40, 15 };
public abstract class SortedSetTests extends SetTests {
	@Test
	@Override
	void toArrayTest() {
		Arrays.sort(expected);
		super.toArrayTest();
	}
	@Test
	void firstTest() {
		assertEquals((Integer)(-5), ((SortedSet<Integer>)collection).first());
	}
	@Test
	void lastTest() {
		assertEquals((Integer)(40), ((SortedSet<Integer>)collection).last());
	}
	@Test
	void ceilingtTest() {
		assertEquals((Integer)(40), ((SortedSet<Integer>)collection).ceiling(40));
		assertEquals((Integer)(40), ((SortedSet<Integer>)collection).ceiling(35));
		assertNull(((SortedSet<Integer>)collection).ceiling(41));
	}
	@Test
	void floorTest() {
		assertEquals((Integer)(-5), ((SortedSet<Integer>)collection).floor(-5));
		assertEquals((Integer)(-5), ((SortedSet<Integer>)collection).floor(0));
		assertEquals((Integer)(15), ((SortedSet<Integer>)collection).floor(16));
		assertNull(((SortedSet<Integer>)collection).floor(-6));
	}
	

}
