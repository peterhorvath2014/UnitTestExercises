package com.epam.calculator;

import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalculatorTest {
	private static Calculator underTest;
	
	@Mock
	private static Sum sum;

	@Mock
	private static Logger logger;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		underTest = new Calculator();
		underTest.setLog(logger);
		underTest.setSum(sum);
	}

	@Test
	public void testSumShouldBeValid() {
		// GIVEN in setup
		BDDMockito.given(sum.calculate(BDDMockito.anyString(), BDDMockito.anyString())).willReturn(Integer.valueOf(5));
		String input = "85,56";
		String separator = ",";
		// WHEN
		Integer result = underTest.sum(input, separator);
		// THEN
		BDDMockito.verify(sum).calculate("0", "85");
		BDDMockito.verify(sum).calculate("5", "56");
		Assert.assertEquals(Integer.valueOf(5), result);
	}

	@Test(enabled = false)
	public void testSumShouldNotAcceptPlusSignAsSeparator() {
		// GIVEN in setup
		String input = "85+56";
		String separator = "+";
		// WHEN
		underTest.sum(input, separator);
		// THEN ??
	}

	@Test
	public void testSumShouldGiveBackTheInputIfItContainsOnlyOneNumber() {
		// GIVEN in setup
		BDDMockito.given(sum.calculate(BDDMockito.anyString(), BDDMockito.anyString())).willReturn(Integer.valueOf(5));
		String input = "8556";
		String separator = ",";
		// WHEN
		Integer result = underTest.sum(input, separator);
		// THEN
		BDDMockito.verify(sum).calculate("0", "8556");
		AssertJUnit.assertEquals(Integer.valueOf(5), result);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testSumShouldGiveBackExceptionIfInputIsNull() {
		// GIVEN in setup
		String input = null;
		String separator = ",";
		// WHEN
		underTest.sum(input, separator);
		// THEN throw IllegalArgumentException
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testSumShouldGiveBackExceptionIfInputIsEmpty() {
		// GIVEN in setup
		String input = "";
		String separator = ",";
		// WHEN
		underTest.sum(input, separator);
		// THEN throw IllegalArgumentException
	}
}
