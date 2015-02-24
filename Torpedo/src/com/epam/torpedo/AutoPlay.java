package com.epam.torpedo;

public class AutoPlay {
	private Matrix guessedMatrix;
	
	public Matrix fireAll(Matrix enemyMatrix) {
		guessedMatrix = new DefaultMatrix();
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				evaluatePoint(enemyMatrix, new Point(i, j));
			}
		}
		
		return guessedMatrix;
	}

	private void evaluatePoint(Matrix enemyMatrix, Point nextPoint) {
		if (enemyMatrix.isFound(nextPoint)) {
			this.guessedMatrix.foundShip(nextPoint);
		}
	}
}
