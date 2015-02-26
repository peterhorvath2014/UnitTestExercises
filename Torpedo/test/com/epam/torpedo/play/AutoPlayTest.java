package com.epam.torpedo.play;

import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.FieldType;
import com.epam.torpedo.field.battlefield.GuessedBattleField;
import com.epam.torpedo.field.battlefield.OwnedBattleField;
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
		guessedBattleField.setCellFieldType(new Coordinate(0,2), FieldType.HIT);
		guessedBattleField.setCellFieldType(new Coordinate(0,3), FieldType.HIT);
		guessedBattleField.setCellFieldType(new Coordinate(0,4), FieldType.HIT);
		guessedBattleField.setCellFieldType(new Coordinate(0,5), FieldType.HIT);
		guessedBattleField.setCellFieldType(new Coordinate(0,6), FieldType.HIT);
		guessedBattleField.setCellFieldType(new Coordinate(0,7), FieldType.HIT);
		guessedBattleField.setCellFieldType(new Coordinate(0,8), FieldType.HIT);
		guessedBattleField.setCellFieldType(new Coordinate(0,9), FieldType.HIT);
		guessedBattleField.setCellFieldType(new Coordinate(1,0), FieldType.HIT);
		guessedBattleField.setCellFieldType(new Coordinate(1,1), FieldType.HIT);
		System.out.println(guessedBattleField);
		// WHEN
		GameState result = underTest.fireAll();
		// THEN
		Assert.assertTrue(result.isWon());
		Assert.assertEquals(result.getAttackCount(), 12);
		
		System.out.println(result.getGuessedBattleField());
		Assert.assertEquals(result.getGuessedBattleField(), guessedBattleField);
	}
}
