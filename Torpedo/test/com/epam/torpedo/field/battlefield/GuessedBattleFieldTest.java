package com.epam.torpedo.field.battlefield;

import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.Cell;

public class GuessedBattleFieldTest {
	
	private GuessedOpponentBattleField underTest;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		underTest = new GuessedOpponentBattleField();
	}
	
	@Test
	public void testGuessedBattleFieldWhenSetUnknownToZeroCoordinateThenGetUnknown() {
		// GIVEN in setup
		Coordinate coordinate = new Coordinate(0,0);
		underTest.setCell(coordinate, Cell.UNKNOWN);
		// WHEN
		Cell result = underTest.getCell(coordinate);
		// THEN throws Exception
		Assert.assertEquals(result, Cell.UNKNOWN);
	}
	
	@Test
	public void testGuessedBattleFieldWhenSetUnknownToDifferentCoordinateThenGetUnknown() {
		// GIVEN in setup
		Coordinate coordinate = new Coordinate(3,4);
		underTest.setCell(coordinate, Cell.UNKNOWN);
		// WHEN
		Cell result = underTest.getCell(coordinate);
		// THEN throws Exception
		Assert.assertEquals(result, Cell.UNKNOWN);
	}
}
