package com.epam.torpedo.field;

import java.util.Random;

public class DefaultBattleField implements BattleField {
	private FieldType[][] battleField;
	private static Random random = new Random();
	private static final int DEFAULT_NUMBER_OF_LIVE_SHIPS = 10;

	public DefaultBattleField() {
		battleField = new FieldType[10][10];
		fillBattleField();
	}

	@Override
	public void fillRandomShips() {
		for (int i = 0; i < DEFAULT_NUMBER_OF_LIVE_SHIPS; i++) {
			FieldCoordinate point;
			do {
				point = new FieldCoordinate(random.nextInt(10), random.nextInt(10));
			} while (isFound(point));
			createShip(point);
		}
	}

	@Override
	public boolean isFound(FieldCoordinate point) {
		return battleField[point.getX()][point.getY()] == FieldType.SHIP_PART;
	}

	@Override
	public void foundShip(FieldCoordinate point) {
		battleField[point.getX()][point.getY()] = FieldType.SHIP_PART;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				char toPrint = '-';
				if (battleField[i][j] == FieldType.SHIP_PART) {
					toPrint = '+';
				}
				sb.append(toPrint);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private void fillBattleField() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				battleField[i][j] = FieldType.EMPTY;
			}
		}
	}

	private void createShip(FieldCoordinate point) {
		battleField[point.getX()][point.getY()] = FieldType.SHIP_PART;
	}

}
