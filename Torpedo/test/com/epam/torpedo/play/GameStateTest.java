package com.epam.torpedo.play;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.battlefield.OwnedBattleField;
import com.epam.torpedo.gameapi.EnemyAPI;

public class GameStateTest {
	private GameState underTest;

	@Mock
	private EnemyAPI enemyAPI;
	
	@Mock
	private OwnedBattleField ownedBattleField;
	
	private GameConfiguration gameConfiguration;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		gameConfiguration = new GameConfiguration(10, 10);
		underTest = new GameState(ownedBattleField, gameConfiguration);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGameStateCreationWhenOwnBattleFieldParameterIsNullThenThorwsException() {
		// GIVEN in setup
		// WHEN
		 new GameState(null, gameConfiguration);
		// THEN throws Exception
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGameStateCreationWhenGameConfigurationParameterIsNullThenThorwsException() {
		// GIVEN in setup
		// WHEN
		 new GameState(ownedBattleField, null);
		// THEN throws Exception
	}
	
	@Test
	public void testsetGuessedBattleFieldCellWhenHitThenCallsGuessedSetCell() {
		// GIVEN in setup
		Coordinate coordinate = new Coordinate(0,0);
		// WHEN
		underTest.setGuessedBattleFieldCell(coordinate, Cell.HIT);
		// THEN throws Exception
		Assert.assertEquals(underTest.getGuessedCellFieldType(coordinate), Cell.HIT);
	}
	
}
