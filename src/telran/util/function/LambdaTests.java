package telran.util.function;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class LambdaTests {
@Test
void sortStringsLength () {
	String stringsInitial[] = {
			"lmn", "abcd", "yy", "z"
	};
	String expected[] = {
			"z", "yy", "lmn", "abcd"
	};
	Arrays.sort(stringsInitial, (s1, s2) -> s1.length() - s2.length());
	assertArrayEquals(expected, stringsInitial);
}
@Test
void sortEvenOddNumbers () {
	//first even numbers
	//even numbers in ascending order
	//odd numbers in descending order
	Integer arrayInitial [] = {13, 20, 12, 1 , 4, 5};
	Integer arrayActual [] = {4, 12, 20, 13, 5, 1};
	Arrays.sort(arrayInitial, (n1, n2) -> {
		if (n1 % 2 == 0 && n2 % 2 == 1) {
			return -1;
		}
		if (n1 % 2 == 1 && n2 % 2 == 0) {
			return 1;
		}
		if (n1 % 2 == 0 && n2 % 2 == 0) {
			return n1 - n2;
		}
		return n2 - n1;
	});
	assertArrayEquals(arrayActual, arrayInitial);
}

}
