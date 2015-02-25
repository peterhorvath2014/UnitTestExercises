package com.epam.torpedo.play;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.FieldType;
import com.epam.torpedo.field.battlefield.OwnedBattleField;
import com.epam.torpedo.gameapi.EnemyAPI;

public class GameStateTest {
	private GameState underTest;

	@Mock
	private EnemyAPI enemyAPI;
	
	@Mock
	private OwnedBattleField ownedBattleField;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		underTest = new GameState(ownedBattleField);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGameStateCreationWhenParameterIsNullThenThorwsException() {
		// GIVEN in setup
		// WHEN
		 new GameState(null);
		// THEN throws Exception
	}
	
	@Test
	public void testFoundShipPartWhenCallThenCallsGuessedFoundShipPart() {
		// GIVEN in setup
		Coordinate coordinate = new Coordinate(0,0);
		// WHEN
		underTest.foundShipPart(coordinate);
		// THEN throws Exception
		Assert.assertEquals(underTest.getGuessedCellFieldType(coordinate), FieldType.HIT);
	}
	
	@Test
	public void testGuessedBattleFieldWhenCreateThenUnknown() {
		// GIVEN in setup
		Coordinate coordinate = new Coordinate(0,0);
		// WHEN
		// THEN throws Exception
		Assert.assertEquals(underTest.getGuessedCellFieldType(coordinate), FieldType.UNKNOWN);
	}
}
