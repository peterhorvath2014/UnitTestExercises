package com.epam.torpedo.field.battlefield;

import java.util.LinkedList;
import java.util.List;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.RealField;
import com.epam.torpedo.field.ship.Ship;
import com.epam.torpedo.field.ship.ShipFactory;

public class HomeBattleField extends RealField {

	public HomeBattleField(GameConfiguration gameConfiguration) {
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
	
	public Cell checkFire(Coordinate coordinate) {
		Cell cell = getCell(coordinate);
		Cell result = Cell.MISSED;
		if (isShipPart(coordinate)) {
			result = Cell.HIT;
		}
		setCell(coordinate, cell);
		if (isSunk(coordinate)) {
			changeAllConnectedHitToSunk(coordinate);
			result = Cell.SUNK;
		}
		addAttackHistory(coordinate);
		return result;
	}

	private void addAttackHistory(Coordinate coordinate) {
		// TODO Auto-generated method stub
		
	}

	private boolean isSunk(Coordinate coordinate) {
		//TODO continue from here...
		// recursive call to all surrounding cell which has HIT on it
		
		for (int i = coordinate.getY() - 1; i <= coordinate.getY() + 1; i++) {
			for (int j = coordinate.getX() - 1; j <= coordinate.getX() + 1; j++) {
				Coordinate connectedCoordinate = new Coordinate(i, j);
				if (!isCoordinateOutOfBounds(connectedCoordinate)) {
					if (isHit(connectedCoordinate)) {
						isSunk(connectedCoordinate);
					}
				}
			}
		}
		return false;
	}

	public boolean isEveryShipSunk() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getAttackHistoryLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	public LinkedList<Coordinate> getAttackHistory() {
		// TODO Auto-generated method stub
		return null;
	}

}
