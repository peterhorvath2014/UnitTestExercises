package com.epam.torpedo.play;

import com.epam.torpedo.field.DefaultBattleField;
import com.epam.torpedo.field.BattleField;
import com.epam.torpedo.field.FieldCoordinate;

public class AutoPlay {
	private BattleField guessedBattleField;
	
	public boolean fireAll(BattleField enemyMatrix) {
		checkParameter(enemyMatrix);
		guessedBattleField = new DefaultBattleField();
		boolean won = false;
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				evaluatePoint(enemyMatrix, new FieldCoordinate(i, j));
				//TODO: check if won
				//if (guessedBattleField.countLiveShips() == NUMBER_OF_LIVE_SHIPS) {
					won = true;
				//}
			}
		}
		
		return won;
	}		

	private void checkParameter(BattleField enemyMatrix) {
		isMatrixNull(enemyMatrix);
	}

	private void isMatrixNull(BattleField enemyMatrix) {
		if (enemyMatrix == null) {
			throw new IllegalArgumentException("enemyMatrix parameter should not be null");
		}
	}

	private void evaluatePoint(BattleField enemyMatrix, FieldCoordinate nextPoint) {
		if (enemyMatrix.isFound(nextPoint)) {
			this.guessedBattleField.foundShip(nextPoint);
		}
	}
}
