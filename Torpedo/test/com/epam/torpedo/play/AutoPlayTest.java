package com.epam.torpedo.play;

import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.GuessedBattleField;
import com.epam.torpedo.field.OwnedBattleField;
import com.epam.torpedo.gameapi.EnemyAPI;

public class AutoPlayTest {

	private AutoPlay underTest;

	@Mock
	private EnemyAPI enemyAPI;
	
	@Mock
	private OwnedBattleField ownedBattleField;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		underTest = new AutoPlay(enemyAPI, ownedBattleField);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testAutoPlayCreationWhenEnemyAPIParameterIsNullThenThorwsException() {
		// GIVEN in setup
		// WHEN
		new AutoPlay(null, ownedBattleField);
		// THEN throws Exception
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testAutoPlayCreationWhenOwnedBattleFieldParameterIsNullThenThorwsException() {
		// GIVEN in setup
		// WHEN
		new AutoPlay(enemyAPI, null);
		// THEN throws Exception
	}

	@Test
	public void testFireAllWhenSuccessfulThenGameStateChanges() {
		// GIVEN in setup
		BDDMockito.given(
				enemyAPI.isShipPart(BDDMockito.any(Coordinate.class)))
				.willReturn(false, false, true);
		GuessedBattleField guessedBattleField = new GuessedBattleField();
		guessedBattleField.foundShipPart(new Coordinate(0,2));
		guessedBattleField.foundShipPart(new Coordinate(0,3));
		guessedBattleField.foundShipPart(new Coordinate(0,4));
		guessedBattleField.foundShipPart(new Coordinate(0,5));
		guessedBattleField.foundShipPart(new Coordinate(0,6));
		guessedBattleField.foundShipPart(new Coordinate(0,7));
		guessedBattleField.foundShipPart(new Coordinate(0,8));
		guessedBattleField.foundShipPart(new Coordinate(0,9));
		guessedBattleField.foundShipPart(new Coordinate(1,0));
		guessedBattleField.foundShipPart(new Coordinate(1,1));
		// WHEN
		GameState result = underTest.fireAll();
		// THEN
		Assert.assertTrue(result.isWon());
		Assert.assertEquals(result.getAttackCount(), 12);
		Assert.assertEquals(result.getGuessedBattleField(), guessedBattleField);
	}
}
