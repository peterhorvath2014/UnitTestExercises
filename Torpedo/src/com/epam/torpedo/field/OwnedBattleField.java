package com.epam.torpedo.field;

public class OwnedBattleField extends AbstractBattleField {
	
	protected void fillBattleField() {
		fillBattleField(FieldType.EMPTY);
		fillRandomShips();
	}

	private void fillRandomShips() {
		for (int i = 0; i < numberOfLiveShipParts; i++) {
			Coordinate point;
			do {
				point = new Coordinate(random.nextInt(sideLength),
						random.nextInt(sideLength));
			} while (isShipPart(point));
			createShip(point);
		}
	}

	private void createShip(Coordinate point) {
		battleField[point.getX()][point.getY()] = FieldType.SHIP_PART;
	}

	public boolean isShipPart(Coordinate point) {
		return battleField[point.getX()][point.getY()] == FieldType.SHIP_PART;
	}

}
