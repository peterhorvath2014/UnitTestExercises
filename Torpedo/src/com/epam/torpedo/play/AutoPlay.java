package com.epam.torpedo.play;

import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.battlefield.OwnedBattleField;
import com.epam.torpedo.gameapi.EnemyAPI;
import com.epam.torpedo.util.Utility;

public class AutoPlay {
	private GameState gameState;
	private EnemyAPI enemyAPI;
	
	public AutoPlay(EnemyAPI enemyAPI, OwnedBattleField ownedBattleField) {
		Utility.validateParameters(enemyAPI, ownedBattleField);
		this.gameState = new GameState(ownedBattleField);
		this.enemyAPI = enemyAPI;
		
	}

	public GameState fireAll() {
		int x = 0;
		while (isIterationStillValid(x)) {
			int y = 0;
			while (isIterationStillValid(y)) {
				evaluatePoint(enemyAPI, new Coordinate(x, y));
				gameState.increaseAttackCount();
				if (gameState.getGuessedBattleField().isDone()) {
					gameState.setWon(true);
				}
				y++;
			}
			x++;
		}
		
		System.out.println(gameState);
		
		return gameState;
	}

	private boolean isIterationStillValid(int x) {
		return !gameState.isWon() && x < gameState.getSideLength();
	}

	private void evaluatePoint(EnemyAPI enemyAPI, Coordinate coordinate) {
		if (enemyAPI.isShipPart(coordinate)) {
			gameState.foundShipPart(coordinate);
		}
	}
}
