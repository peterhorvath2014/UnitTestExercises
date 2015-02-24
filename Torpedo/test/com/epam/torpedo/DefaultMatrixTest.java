package com.epam.torpedo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DefaultMatrixTest {
	
	@Test
	public void testCountLiveShipsWhenNotCallingFillThenZero() {
		// GIVEN
		DefaultMatrix underTest = new DefaultMatrix();
		// WHEN
		int result = underTest.countLiveShips();
		// THEN
		Assert.assertEquals(result, 0);
	}
	
	@Test
	public void testFillRandomShipsWhenCallFillThenCountIsTen() {
		// GIVEN
		DefaultMatrix underTest = new DefaultMatrix();
		// WHEN
		underTest.fillRandomShips();
		int numberOfLiveShips = underTest.countLiveShips();
		// THEN
		Assert.assertEquals(numberOfLiveShips, 10);
	}
	
}
