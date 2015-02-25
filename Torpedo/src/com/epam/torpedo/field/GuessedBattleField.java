package com.epam.torpedo.field;

public class GuessedBattleField extends AbstractBattleField {

	public void foundShip(FieldCoordinate point) {
		battleField[point.getX()][point.getY()] = FieldType.HIT;
	}

	protected void fillBattleField() {
		fillBattleField(FieldType.UNKNOWN);
	}

}
