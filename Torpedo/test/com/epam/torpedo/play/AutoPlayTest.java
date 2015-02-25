package com.epam.torpedo.play;

import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.torpedo.field.Coordinate;
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
	public void testFireAllWhenSuccessfulThenReturnsTrue() {
		// GIVEN in setup
		BDDMockito.given(
				enemyAPI.isShipPart(BDDMockito.any(Coordinate.class)))
				.willReturn(true);
		// WHEN
		GameState result = underTest.fireAll();
		// THEN
		Assert.assertTrue(result.isWon());
	}
}
