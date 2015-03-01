package com.epam.torpedo.play;

import com.epam.torpedo.communication.gameapi.EnemyAPI;
import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.battlefield.OwnedBattleField;
import com.epam.torpedo.util.Utility;

public class AutoPlay {
	private GameState gameState;
	private EnemyAPI enemyAPI;
	private OwnedBattleField ownedBattleField;
	
	public AutoPlay(EnemyAPI enemyAPI, OwnedBattleField ownedBattleField) {
		Utility.validateParameters(enemyAPI, ownedBattleField);
		GameConfiguration gameConfiguration = enemyAPI.hello();
		this.gameState = new GameState(ownedBattleField, gameConfiguration);
		this.enemyAPI = enemyAPI;
		this.ownedBattleField = ownedBattleField;
	}

	public GameState fireAll() {
		int y = 0;
		while (isIterationStillValidY(y)) {
			int x = 0;
			while (isIterationStillValidX(x)) {
				evaluatePoint(enemyAPI, new Coordinate(y, x));
				gameState.increaseAttackCount();
				int numberOfLiveShipParts = ownedBattleField.getNumberOfLiveShipParts();
				boolean done = gameState.getGuessedBattleField().isDone(numberOfLiveShipParts);
				if (done) {
					gameState.setWon(true);
				}
				x++;
			}
			y++;
		}
		
		return gameState;
	}

	private boolean isIterationStillValidX(int x) {
		return !gameState.isWon() && x < gameState.getSideLengthX();
	}
	private boolean isIterationStillValidY(int y) {
		return !gameState.isWon() && y < gameState.getSideLengthY();
	}

	private void evaluatePoint(EnemyAPI enemyAPI, Coordinate coordinate) {
		Cell result = enemyAPI.shoot(coordinate);
		gameState.setGuessedBattleFieldCell(coordinate, result);
	}
}
