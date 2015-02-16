package com.epam.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

class LoggerStub extends Logger {
	public void logInfo(String msg) {}
}

class SumStub extends Sum {
	public Integer calculate(String result, String number) {
		return Integer.valueOf(0);
	}
}

public class CalculatorTest {
	private static Calculator underTest;
	private static LoggerStub logger;
	private static SumStub sum;

	@BeforeClass
	public static void setUp() {
		logger = new LoggerStub();
		sum = new SumStub();
		underTest = new Calculator();
		underTest.setLog(logger);
		underTest.setSum(sum);
	}

	@Test
	public void testSumShouldBeValid() {
		// GIVEN in setUp()
		// WHEN
		String input = "85,56";
		String separator = ",";
		// THEN
		assertEquals(Integer.valueOf(0), underTest.sum(input, separator));
	}

	@Ignore
	public void testSumShouldNotAcceptPlusSignAsSeparator() {
		// GIVEN in setUp()
		// WHEN
		String input = "85+56";
		String separator = "+";
		// THEN
		underTest.sum(input, separator);
	}

	@Test
	public void testSumShouldGiveBackTheInputIfItContainsOnlyOneNumber() {
		// GIVEN in setUp()
		// WHEN
		String input = "8556";
		String separator = ",";
		// THEN
		assertEquals(Integer.valueOf(0), underTest.sum(input, separator));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSumShouldGiveBackExceptionIfInputIsNull() {
		// GIVEN in setUp()
		// WHEN
		String input = null;
		String separator = ",";
		// THEN
		underTest.sum(input, separator);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSumShouldGiveBackExceptionIfInputIsEmpty() {
		// GIVEN in setUp()
		// WHEN
		String input = "";
		String separator = ",";
		// THEN
		underTest.sum(input, separator);
	}
}
