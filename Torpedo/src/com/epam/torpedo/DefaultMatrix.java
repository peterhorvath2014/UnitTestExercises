package com.epam.torpedo;

import java.util.Random;

public class DefaultMatrix implements Matrix {
	private Boolean[][] matrix;
	private static Random random = new Random();
	private static final int DEFAULT_NUMBER_OF_LIVE_SHIPS = 10;

	public DefaultMatrix() {
		matrix = new Boolean[10][10];
		fillMatrix();
	}

	@Override
	public void fillRandomShips() {
		for (int i = 0; i < DEFAULT_NUMBER_OF_LIVE_SHIPS; i++) {
			Point point;
			do {
				point = new Point(random.nextInt(10), random.nextInt(10));
			} while (isShipExists(point));
			createShip(point);
		}
	}

	@Override
	public int countLiveShips() {
		int count = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (matrix[i][j]) {
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public boolean isFound(Point point) {
		return matrix[point.getX()][point.getY()];
	}

	@Override
	public void foundShip(Point point) {
		matrix[point.getX()][point.getY()] = true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				char toPrint = '-';
				if (matrix[i][j]) {
					toPrint = '+';
				}
				sb.append(toPrint);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private void fillMatrix() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				matrix[i][j] = false;
			}
		}
	}

	private void createShip(Point point) {
		matrix[point.getX()][point.getY()] = true;
	}

	private boolean isShipExists(Point point) {
		return matrix[point.getX()][point.getY()];
	}

}
