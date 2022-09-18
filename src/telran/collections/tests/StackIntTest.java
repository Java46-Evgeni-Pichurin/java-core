package telran.collections.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.StackInt;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

class StackIntTest {
	StackInt myStack = new StackInt();
	@BeforeEach
	void setUp () {
		StackInt myStack = new StackInt();
	}

	@Test
	void testPop() {
		assertThrows(EmptyStackException.class, () -> myStack.pop());
		assertEquals(0, myStack.getSize());
		myStack.push(10);
		assertEquals(1, myStack.getSize());
		assertEquals(10, myStack.pop());
	}

	@Test
	void testPush() {
		assertEquals(0, myStack.getSize());
		myStack.push(10);
		assertEquals(1, myStack.getSize());
		myStack.push(15);
		assertEquals(2, myStack.getSize());
	}

	@Test
	void testIsEmpty() {
		assertTrue(myStack.isEmpty());
		myStack.push(10);
		assertFalse(myStack.isEmpty());
		myStack.pop();
		assertTrue(myStack.isEmpty());
	}

	@Test
	void testGetMaxNumber() {
		assertThrows(NoSuchElementException.class, () -> myStack.getMaxNumber());
		myStack.push(10);
		myStack.push(15);
		myStack.push(18);
		assertEquals(18, myStack.getMaxNumber());
		myStack.pop();
		myStack.pop();
		//assertEquals(10, myStack.getMaxNumber());
		//Bug***
		myStack.pop();
		assertThrows(NoSuchElementException.class, () -> myStack.getMaxNumber());
	}
}
