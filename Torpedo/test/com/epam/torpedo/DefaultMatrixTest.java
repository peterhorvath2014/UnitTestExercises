package com.epam.torpedo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DefaultMatrixTest {
	
	@Test
	public void testFillRandomShipsWhenCallFillThenCountIsTen() {
		// GIVEN
		DefaultMatrix underTest = new DefaultMatrix();
		// WHEN
		underTest.fillRandomShips();
		// THEN
		Assert.assertEquals(underTest.countLiveShips(), 10);
		System.out.println(underTest);
	}
	
}
