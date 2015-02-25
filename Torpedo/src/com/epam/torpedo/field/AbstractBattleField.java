package com.epam.torpedo.field;

import java.util.Arrays;
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
		sb.append("\n");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				char toPrint = '.';
				if ((battleField[i][j] == FieldType.SHIP_PART) || (battleField[i][j] == FieldType.HIT)) {
					toPrint = 'x';
				} else if ((battleField[i][j] == FieldType.DENIED) || (battleField[i][j] == FieldType.MISSED)) {
					toPrint = '+';
				}
				sb.append(toPrint + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(battleField);
		result = prime * result + numberOfLiveShipParts;
		result = prime * result + sideLength;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractBattleField other = (AbstractBattleField) obj;
		if (!Arrays.deepEquals(battleField, other.battleField))
			return false;
		if (numberOfLiveShipParts != other.numberOfLiveShipParts)
			return false;
		if (sideLength != other.sideLength)
			return false;
		return true;
	}

	protected int countHitShipParts() {
		int count = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (battleField[i][j] == FieldType.HIT) {
					count++;
				}
			}
		}
		return count;
	}
	
	public FieldType getCellFieldType(Coordinate coordinate) {
		return battleField[coordinate.getX()][coordinate.getY()];
	}

}
