package com.epam.torpedo.field;

public class OwnedBattleField extends AbstractBattleField {
	
	protected void fillBattleField() {
		fillBattleField(FieldType.EMPTY);
		fillRandomShips();
	}

	private void fillRandomShips() {
		for (int i = 0; i < numberOfLiveShipParts; i++) {
			FieldCoordinate point;
			do {
				point = new FieldCoordinate(random.nextInt(sideLength),
						random.nextInt(sideLength));
			} while (isShipPart(point));
			createShip(point);
		}
	}

	private void createShip(FieldCoordinate point) {
		battleField[point.getX()][point.getY()] = FieldType.SHIP_PART;
	}

	public boolean isShipPart(FieldCoordinate point) {
		return battleField[point.getX()][point.getY()] == FieldType.SHIP_PART;
	}

}
