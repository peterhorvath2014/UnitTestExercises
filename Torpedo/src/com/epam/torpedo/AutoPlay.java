package com.epam.torpedo;

public class AutoPlay {
	private static final int NUMBER_OF_LIVE_SHIPS = 10;
	private Matrix guessedMatrix;
	
	public boolean fireAll(Matrix enemyMatrix) {
		checkParameter(enemyMatrix);
		guessedMatrix = new DefaultMatrix();
		boolean won = false;
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				evaluatePoint(enemyMatrix, new Point(i, j));
				if (guessedMatrix.countLiveShips() == NUMBER_OF_LIVE_SHIPS) {
					won = true;
				}
			}
		}
		
		return won;
	}		

	private void checkParameter(Matrix enemyMatrix) {
		isMatrixNull(enemyMatrix);
		isNumberOfLiveShipsCorrect(enemyMatrix);
	}

	private void isNumberOfLiveShipsCorrect(Matrix enemyMatrix) {
		if (enemyMatrix.countLiveShips() != NUMBER_OF_LIVE_SHIPS) {
			throw new IllegalArgumentException("enemyMatrix parameter should contain 10 live ships");
		}
	}

	private void isMatrixNull(Matrix enemyMatrix) {
		if (enemyMatrix == null) {
			throw new IllegalArgumentException("enemyMatrix parameter should not be null");
		}
	}

	private void evaluatePoint(Matrix enemyMatrix, Point nextPoint) {
		if (enemyMatrix.isFound(nextPoint)) {
			this.guessedMatrix.foundShip(nextPoint);
		}
	}
}
