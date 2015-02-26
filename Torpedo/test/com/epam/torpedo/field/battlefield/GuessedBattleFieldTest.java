package com.epam.torpedo.field.battlefield;

import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.FieldType;

public class GuessedBattleFieldTest {
	
	private GuessedBattleField underTest;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		underTest = new GuessedBattleField();
	}
	
	@Test
	public void testGuessedBattleFieldWhenSetUnknownToZeroCoordinateThenGetUnknown() {
		// GIVEN in setup
		Coordinate coordinate = new Coordinate(0,0);
		underTest.setCellFieldType(coordinate, FieldType.UNKNOWN);
		// WHEN
		FieldType result = underTest.getCellFieldType(coordinate);
		// THEN throws Exception
		Assert.assertEquals(result, FieldType.UNKNOWN);
	}
	
	@Test
	public void testGuessedBattleFieldWhenSetUnknownToDifferentCoordinateThenGetUnknown() {
		// GIVEN in setup
		Coordinate coordinate = new Coordinate(3,4);
		underTest.setCellFieldType(coordinate, FieldType.UNKNOWN);
		// WHEN
		FieldType result = underTest.getCellFieldType(coordinate);
		// THEN throws Exception
		Assert.assertEquals(result, FieldType.UNKNOWN);
	}
}
