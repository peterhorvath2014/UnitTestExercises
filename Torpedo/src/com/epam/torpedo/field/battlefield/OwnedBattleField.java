package com.epam.torpedo.field.battlefield;

import com.epam.torpedo.field.AbstractField;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.FieldType;
import com.epam.torpedo.field.config.GameConfiguration;

public class OwnedBattleField extends AbstractField {
	
	protected void fillField() {
		fillField(FieldType.EMPTY, GameConfiguration.DEFAULT_BATTLE_FIELD_SIDE_LENGTH);
		fillRandomShips();
	}

	private void fillRandomShips() {
		for (int i = 0; i < numberOfLiveShipParts; i++) {
			Coordinate point;
			do {
				point = new Coordinate(random.nextInt(sideLength),
						random.nextInt(sideLength));
			} while (isShipPart(point));
			createShipPart(point);
		}
	}

	private void createShipPart(Coordinate coordinate) {
		setCellFieldType(coordinate, FieldType.SHIP_PART);
	}

	public boolean isShipPart(Coordinate coordinate) {
		return getCellFieldType(coordinate) == FieldType.SHIP_PART;
	}

}