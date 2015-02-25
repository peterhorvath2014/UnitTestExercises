package com.epam.torpedo.field;

import java.util.Random;

public abstract class AbstractBattleField {

	protected FieldType[][] battleField;

	protected static Random random = new Random();

	protected static final int DEFAULT_NUMBER_OF_LIVE_SHIP_PARTS = 10;
	protected static final int DEFAULT_SIDE_LENGTH = 10;

	protected int numberOfLiveShipParts;
	protected int sideLength;

	public AbstractBattleField() {
		this(DEFAULT_SIDE_LENGTH, DEFAULT_NUMBER_OF_LIVE_SHIP_PARTS);
	}

	public AbstractBattleField(int sideLength, int numberOfLiveShipParts) {
		this.sideLength = sideLength;
		this.numberOfLiveShipParts = numberOfLiveShipParts;
		battleField = new FieldType[sideLength][sideLength];
		fillBattleField();
	}

	protected abstract void fillBattleField();

	protected void fillBattleField(FieldType type) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				battleField[i][j] = type;
			}
		}
	}

	public int getNumberOfLiveShipParts() {
		return numberOfLiveShipParts;
	}

	public int getSideLength() {
		return sideLength;
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

}
