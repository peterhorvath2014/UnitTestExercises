package com.epam.alltogether;

import org.junit.Assert;
import org.junit.Test;

public class NumberFormatterTest {
	
	@Test
	public void testFormat() {
		// GIVEN
		NumberFormatter numberFormatter = new NumberFormatter();
		
		// WHEN
		String result = numberFormatter.format(2);
		
		// THEN
		Assert.assertEquals("[2]" , result);
	}
}
