package com.epam.alltogether;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AbstractCalculatorTest {
	private static AbstractCalculator underTest;
	
	@BeforeClass
	public static void setUp() {
		underTest = new AbstractCalculator() {
			@Override
			protected Integer doCalcualte(Integer a, Integer b) {
				return Integer.valueOf(2);
			}
		};
	}
	
	@Test
	public void testCalculateShouldCallDoCalculate() {
		// GIVEN in setup
        // WHEN
        Integer result = underTest.calculate(Integer.valueOf(1), Integer.valueOf(1));
        // THEN
        Assert.assertEquals(Integer.valueOf(2), result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateShouldThrowExceptionWhenLeftParameterIsNull() {
		// GIVEN in setup
        // WHEN
        underTest.calculate(null, Integer.valueOf(1));
        // THEN exception thrown
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateShouldThrowExceptionWhenRightParameterIsNull() {
		// GIVEN in setup
        // WHEN
        underTest.calculate(Integer.valueOf(1), null);
        // THEN exception thrown
	}
	
}
