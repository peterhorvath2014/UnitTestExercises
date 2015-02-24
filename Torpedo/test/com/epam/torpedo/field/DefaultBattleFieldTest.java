package com.epam.torpedo.field;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.torpedo.field.DefaultBattleField;

public class DefaultBattleFieldTest {
	
	@Test
	public void testCountLiveShipsWhenNotCallingFillThenZero() {
		// GIVEN
		DefaultBattleField underTest = new DefaultBattleField();
		// WHEN
		int result = underTest.countLiveShips();
		// THEN
		Assert.assertEquals(result, 0);
	}
	
	@Test
	public void testFillRandomShipsWhenCallFillThenCountIsTen() {
		// GIVEN
		DefaultBattleField underTest = new DefaultBattleField();
		// WHEN
		underTest.fillRandomShips();
		int numberOfLiveShips = underTest.countLiveShips();
		// THEN
		Assert.assertEquals(numberOfLiveShips, 10);
	}
	
}
