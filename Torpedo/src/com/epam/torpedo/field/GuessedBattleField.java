package com.epam.torpedo.field;

public class GuessedBattleField extends AbstractBattleField {

	public void foundShipPart(Coordinate point) {
		battleField[point.getX()][point.getY()] = FieldType.HIT;
	}

	protected void fillBattleField() {
		fillBattleField(FieldType.UNKNOWN);
	}

	public boolean isDone() {
		return countHitShipParts() == numberOfLiveShipParts;
	}

}
