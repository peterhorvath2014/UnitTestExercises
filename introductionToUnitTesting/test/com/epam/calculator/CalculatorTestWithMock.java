package com.epam.calculator;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CalculatorTestWithMock {
	private static Calculator underTest;
	private static Logger logger;
	private static SumStub sum;

	@BeforeClass
	public static void setUp() {
		logger = EasyMock.createMock(Logger.class);
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
		logger.logInfo(EasyMock.anyString());
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
