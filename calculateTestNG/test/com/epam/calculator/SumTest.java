package com.epam.calculator;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SumTest {
	private Sum underTest;

	@BeforeClass
	public void setUp() {
		underTest = new Sum();
	}

	@Test
	public void testSumShouldCalculateSum() {
		// GIVEN in setup and here
		// WHEN
		Integer result = underTest.calculate("2", "4");
		// THEN
		Assert.assertEquals(Integer.valueOf(6), result);

	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testSumShouldThrowIllegalArgumentExceptionWhenLeftParameterIsNull() {
		// GIVEN in setup and here
		// WHEN
		underTest.calculate(null, "4");
		// THEN Exception thrown
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testSumShouldThrowIllegalArgumentExceptionWhenRightParameterIsNull() {
		// GIVEN in setup and here
		// WHEN
		underTest.calculate("2", null);
		// THEN Exception thrown
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testSumShouldThrowIllegalArgumentExceptionWhenParameterIsNotParsable() {
		// GIVEN in setup and here
		// WHEN
		underTest.calculate("test", "4");
		// THEN Exception thrown
	}
}
