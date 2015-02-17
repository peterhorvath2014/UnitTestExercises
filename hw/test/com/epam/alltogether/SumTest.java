package com.epam.alltogether;

import org.junit.Assert;
import org.junit.Test;

public class SumTest {
	
	@Test
	public void testDoCalculate() {
		// GIVEN
		Sum sum = new Sum();
		
		// WHEN
		Integer result = sum.calculate(1, 1);
		
		// THEN
		Assert.assertEquals(Integer.valueOf(2), result);
	}
}
