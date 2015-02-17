package com.epam.alltogether;

import org.junit.Assert;
import org.junit.Ignore;
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
	
	@Ignore
	public void testFormatShouldGiveNull() {
		// GIVEN
		NumberFormatter numberFormatter = new NumberFormatter();
		
		// WHEN
		String result = numberFormatter.format(null);
		
		// THEN
		Assert.assertEquals("[null]" , result);
	}
}
