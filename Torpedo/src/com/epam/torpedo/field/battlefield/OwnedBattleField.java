package com.epam.torpedo.field.battlefield;

import java.util.List;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.Field;
import com.epam.torpedo.field.ship.Ship;
import com.epam.torpedo.field.ship.ShipFactory;

public class OwnedBattleField extends Field {

	public OwnedBattleField(GameConfiguration gameConfiguration) {
		super(gameConfiguration);
		fillFieldWithShipsFromFileOnRandomPosition();
	}

	private void fillFieldWithShipsFromFileOnRandomPosition() {
		List<Ship> ships = ShipFactory.createShipsFromFile();
		// TODO check if ships fit to battleField
		// TODO make it random
		Coordinate coordinate = new Coordinate(0, 0);
		for (Ship ship : ships) {
			addFieldToPosition(ship.getField(), coordinate);
			int newX = coordinate.getX() + 5;
			int newY = coordinate.getY();
			if (newX > 5) {
				newX = 0;
				newY = 5;
			}
			coordinate = new Coordinate(newY, newX);
		}
	}

	@Override
	protected Cell getDefaultFillingType() {
		return Cell.EMPTY;
	}

}
