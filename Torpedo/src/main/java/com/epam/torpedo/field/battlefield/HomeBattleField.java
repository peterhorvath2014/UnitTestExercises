package com.epam.torpedo.field.battlefield;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.RealField;
import com.epam.torpedo.field.ship.Ship;
import com.epam.torpedo.field.ship.ShipFactory;

public class HomeBattleField extends RealField implements AttackHistoryHolder {
	private static final Logger logger = LogManager.getLogger();
	private NavigableMap<Coordinate, Cell> attackHistory = new TreeMap<Coordinate, Cell>();

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
	public NavigableMap<Coordinate, Cell> getAttackHistory() {
		return attackHistory;
	}

	@Override
	public void addAttackHistory(Coordinate coordinate, Cell cell) {
		attackHistory.put(coordinate, cell);
		logger.debug(this.toString());
	}

	@Override
	protected Cell getDefaultFillingType() {
		return Cell.EMPTY;
	}

	public Cell checkFire(Coordinate coordinate) {
		Cell result = Cell.MISSED;
		if (isShipPart(coordinate)) {
			result = Cell.HIT;
		}
		if (result == Cell.HIT && isSunk(coordinate)) {
			changeAllConnectedHitToSunk(coordinate);
			result = Cell.SUNK;
		}
		setCell(coordinate, result);
		return result;
	}

	private boolean isSunk(Coordinate coordinate) {
		HashMap<Coordinate, Cell> connectedCells = new HashMap<Coordinate, Cell>();
		getConnectedCells(coordinate, connectedCells);
		connectedCells.remove(coordinate);
		logger.debug(connectedCells);
		return !connectedCells.containsValue(Cell.SHIP_PART);
	}
	
	private void getConnectedCells(Coordinate coordinate, Map<Coordinate, Cell> connectedCells) {
		for (int i = coordinate.getY() - 1; i <= coordinate.getY() + 1; i++) {
			for (int j = coordinate.getX() - 1; j <= coordinate.getX() + 1; j++) {
				Coordinate connectedCoordinate = new Coordinate(i, j);
				if (!isCoordinateOutOfBounds(connectedCoordinate)) {
					if (isHit(connectedCoordinate) || isShipPart(connectedCoordinate)) {
						if (!connectedCells.containsKey(connectedCoordinate)) {
							connectedCells.put(connectedCoordinate, getCell(connectedCoordinate));
							getConnectedCells(connectedCoordinate, connectedCells);
						}
					} 
				}
			}
		}
	}

	public boolean isEveryShipSunk() {
		for (List<Cell> row : field) {
			for (Cell element : row) {
				if (element == Cell.SHIP_PART) {
					return false;
				}
			}
		}
		return true;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		for (List<Cell> line : field) {
			for (Cell cell : line) {
				String displayString = cell.name().substring(0, 2);
				if (displayString.equals("SH") || displayString.equals("DE") || displayString.equals("EM")) {
					displayString = displayString.toLowerCase();
				}
				sb.append(displayString + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
