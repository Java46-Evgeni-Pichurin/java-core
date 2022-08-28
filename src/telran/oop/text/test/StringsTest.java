package telran.oop.text.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.oop.text.Strings;

class StringsTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSortStringsAsDates() {
		String input[] = {"10/08/2021", "30/12/2020", "5/08/2021"};
		String expected[] = {"30/12/2020", "5/08/2021", "10/08/2021"};
		assertArrayEquals(expected, Strings.sortStringsAsDates(input));
	}

}
