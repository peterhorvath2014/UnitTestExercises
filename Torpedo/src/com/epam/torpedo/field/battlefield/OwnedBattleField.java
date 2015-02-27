package com.epam.torpedo.field.battlefield;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.Field;
import com.epam.torpedo.field.Cell;

public class OwnedBattleField extends Field {
	
	protected void fillField() {
		fillField(Cell.EMPTY, GameConfiguration.DEFAULT_BATTLE_FIELD_SIDE_LENGTH);
		fillRandomShips();
	}

	private void fillRandomShips() {
		for (int i = 0; i < numberOfLiveShipParts; i++) {
			Coordinate point;
			do {
				point = new Coordinate(random.nextInt(getSideLengthX()),
						random.nextInt(getSideLengthY()));
			} while (isShipPart(point));
			createShipPart(point);
		}
	}

	private void createShipPart(Coordinate coordinate) {
		setCellFieldType(coordinate, Cell.SHIP_PART);
	}

	public boolean isShipPart(Coordinate coordinate) {
		return getCellFieldType(coordinate) == Cell.SHIP_PART;
	}
	
	@Override
	protected Cell getDefaultFillingType() {
		return Cell.EMPTY;
	}

}
