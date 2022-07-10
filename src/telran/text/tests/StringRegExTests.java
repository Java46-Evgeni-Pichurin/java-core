package telran.text.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.text.Strings.*;

import org.junit.jupiter.api.Test;

class StringRegExTests {

	@Test
	void javaVariableTrue() {
		assertTrue(isJavaVariable("__"));
		assertTrue(isJavaVariable("$"));
		assertTrue(isJavaVariable("$_"));
		assertTrue(isJavaVariable("abc"));
		assertTrue(isJavaVariable("abc100"));
		assertTrue(isJavaVariable("$2"));
		assertTrue(isJavaVariable("_2"));
	}
	@Test
	void javaVariableFalse() {
		assertFalse(isJavaVariable("2_"));
		assertFalse(isJavaVariable("_"));
		assertFalse(isJavaVariable("a 2"));
	}
	@Test
	void ipV4True() {
		assertTrue(isIPv4("0.0.0.0"));
		assertTrue(isIPv4("250.0.199.200"));
		assertTrue(isIPv4("250.0.199.249"));
		assertTrue(isIPv4("001.0.2.0"));
		assertTrue(isIPv4("255.255.255.255"));
	}
	@Test
	void ipV4False() {
		assertFalse(isIPv4("0.0.0.0.0"));
		assertFalse(isIPv4("0.*.10.0"));
		assertFalse(isIPv4("-10.0.0.0"));
		assertFalse(isIPv4("100..10.1.1"));
		assertFalse(isIPv4("100.10.1.256"));
		assertFalse(isIPv4("a.10.1.1"));
	}
	@Test
	void ArithmeticExpressionTrue() {
		assertTrue(isArithmeticExpression("22+33"));
		//assertTrue(isArithmeticExpression("1 + 2 -(a*7)/ C"));
		assertTrue(isArithmeticExpression("1 + 2 -(a*7)/ 7"));
		assertTrue(isArithmeticExpression("(1 + 2 -(a*      7)/ $)"));
	}
	@Test
	void ArithmeticExpressionFalse() {
		assertFalse(isArithmeticExpression("a+b--"));
		assertFalse(isArithmeticExpression("1 + )2 -(a*7)/ _C"));
		assertFalse(isArithmeticExpression("1  2+ 2 -(a*7)/ 5$"));
		assertFalse(isArithmeticExpression("(1 + 2 -(a*   (/)   7)/ 5$)"));
		assertFalse(isArithmeticExpression("(1 + 2 -(a*   []   7)/ 5$)"));
	}
}
