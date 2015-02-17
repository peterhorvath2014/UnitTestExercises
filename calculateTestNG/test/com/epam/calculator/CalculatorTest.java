package com.epam.calculator;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalculatorTest {
	private static Calculator underTest;
	private static SumStub sum;

	@Mock
	private static Logger logger;

	class SumStub extends Sum {
		public Integer calculate(String result, String number) {
			return Integer.valueOf(0);
		}
	}

	@BeforeMethod
	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		sum = new SumStub();
		underTest = new Calculator();
		underTest.setLog(logger);
		underTest.setSum(sum);
	}

	@Test
	public void testSumShouldBeValid() {
		// GIVEN in setup
		String input = "85,56";
		String separator = ",";
		// WHEN
		Integer result = underTest.sum(input, separator);
		// THEN
		Assert.assertEquals(Integer.valueOf(0), result);
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
		String input = "8556";
		String separator = ",";
		// WHEN
		Integer result = underTest.sum(input, separator);
		// THEN
		AssertJUnit.assertEquals(Integer.valueOf(0), result);
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
