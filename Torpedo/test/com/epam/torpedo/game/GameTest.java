package com.epam.torpedo.game;

public class GameTest {

	/*private Strategy underTest;

	private OwnedBattleField ownedBattleField;

	private GuessedBattleField guessedBattleField;

	private GameConfiguration gameConfiguration;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		gameConfiguration = new GameConfiguration(10, 10);
		ownedBattleField = new OwnedBattleField(gameConfiguration);
		ownedBattleField.setCellRectangle(new Coordinate(0, 0), new Coordinate(
				0, 9), Cell.SHIP_PART);
		//BDDMockito.given(clientAPI.hello()).willReturn(gameConfiguration);
		underTest = new ShootEveryCellOneByOne(gameConfiguration);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testAutoPlayCreationWhenEnemyAPIParameterIsNullThenThorwsException() {
		// GIVEN in setup
		// WHEN
		new ShootEveryCellOneByOne(null, ownedBattleField);
		// THEN throws Exception
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testAutoPlayCreationWhenOwnedBattleFieldParameterIsNullThenThorwsException() {
		// GIVEN in setup
		// WHEN
		new ShootEveryCellOneByOne(clientAPI, null);
		// THEN throws Exception
	}

	private void setBasicPlay() {
		BDDMockito.given(clientAPI.shoot(BDDMockito.any(Coordinate.class)))
				.willReturn(Cell.MISSED, Cell.MISSED, Cell.HIT, Cell.HIT,
						Cell.HIT, Cell.HIT, Cell.HIT, Cell.HIT, Cell.HIT,
						Cell.HIT, Cell.MISSED, Cell.MISSED, Cell.HIT, Cell.SUNK);
	}

	@Test
	public void testFireAllWhenSuccessfulThenWonOfGameStateIsTrue() {
		// GIVEN in setup
		setBasicPlay();
		// WHEN
		GameState result = underTest.fireAll();
		// THEN
		Assert.assertTrue(result.isWon());
	}

	@Test
	public void testFireAllWhenSuccessfulThenAttackCountOfGameStateIs12() {
		// GIVEN in setup
		setBasicPlay();
		// WHEN
		GameState result = underTest.fireAll();
		// THEN
		Assert.assertEquals(result.getAttackCount(), 14);
	}

	@Test
	public void testFireAllWhenSuccessfulThenGuessedBattleFieldIsFilledCorrectly() {
		// GIVEN in setup
		setBasicPlay();
		setBasicGuessedBattleField();
		// WHEN
		GameState result = underTest.fireAll();
		// THEN
		Assert.assertEquals(result.getGuessedBattleField(), guessedBattleField);
	}

	private void setBasicGuessedBattleField() {
		guessedBattleField = new GuessedBattleField(gameConfiguration);
		guessedBattleField.setCell(new Coordinate(0, 0), Cell.MISSED);
		guessedBattleField.setCell(new Coordinate(0, 1), Cell.MISSED);
		guessedBattleField.setCell(new Coordinate(0, 2), Cell.SUNK);
		guessedBattleField.setCell(new Coordinate(0, 3), Cell.SUNK);
		guessedBattleField.setCell(new Coordinate(0, 4), Cell.SUNK);
		guessedBattleField.setCell(new Coordinate(0, 5), Cell.SUNK);
		guessedBattleField.setCell(new Coordinate(0, 6), Cell.SUNK);
		guessedBattleField.setCell(new Coordinate(0, 7), Cell.SUNK);
		guessedBattleField.setCell(new Coordinate(0, 8), Cell.SUNK);
		guessedBattleField.setCell(new Coordinate(0, 9), Cell.SUNK);
		guessedBattleField.setCell(new Coordinate(1, 0), Cell.MISSED);
		guessedBattleField.setCell(new Coordinate(1, 1), Cell.MISSED);
		guessedBattleField.setCell(new Coordinate(1, 2), Cell.SUNK);
		guessedBattleField.setCell(new Coordinate(1, 3), Cell.SUNK);
	}*/
}
