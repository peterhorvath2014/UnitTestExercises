package com.epam.torpedo.play;

import com.epam.torpedo.field.DefaultBattleField;
import com.epam.torpedo.field.BattleField;
import com.epam.torpedo.field.FieldCoordinate;

public class AutoPlay {
	private static final int NUMBER_OF_LIVE_SHIPS = 10;
	private BattleField guessedMatrix;
	
	public boolean fireAll(BattleField enemyMatrix) {
		checkParameter(enemyMatrix);
		guessedMatrix = new DefaultBattleField();
		boolean won = false;
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				evaluatePoint(enemyMatrix, new FieldCoordinate(i, j));
				if (guessedMatrix.countLiveShips() == NUMBER_OF_LIVE_SHIPS) {
					won = true;
				}
			}
		}
		
		return won;
	}		

	private void checkParameter(BattleField enemyMatrix) {
		isMatrixNull(enemyMatrix);
		isNumberOfLiveShipsCorrect(enemyMatrix);
	}

	private void isNumberOfLiveShipsCorrect(BattleField enemyMatrix) {
		if (enemyMatrix.countLiveShips() != NUMBER_OF_LIVE_SHIPS) {
			throw new IllegalArgumentException("enemyMatrix parameter should contain 10 live ships");
		}
	}

	private void isMatrixNull(BattleField enemyMatrix) {
		if (enemyMatrix == null) {
			throw new IllegalArgumentException("enemyMatrix parameter should not be null");
		}
	}

	private void evaluatePoint(BattleField enemyMatrix, FieldCoordinate nextPoint) {
		if (enemyMatrix.isFound(nextPoint)) {
			this.guessedMatrix.foundShip(nextPoint);
		}
	}
}
