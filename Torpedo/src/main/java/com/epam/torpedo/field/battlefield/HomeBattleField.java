package com.epam.torpedo.field.battlefield;

import java.util.ArrayList;
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
import com.epam.torpedo.util.Utility;

public class HomeBattleField extends RealField implements AttackHistoryHolder {
	private static final Logger logger = LogManager.getLogger();
	private NavigableMap<Coordinate, Cell> attackHistory = new TreeMap<Coordinate, Cell>();

	public HomeBattleField(GameConfiguration gameConfiguration) {
		super(gameConfiguration);
		if (!field.isEmpty()) {
			int tryAgain = 0;
			int maxTrial = 10;
			while (tryAgain < maxTrial) {
				try {
					fillFieldWithShipsFromFileOnRandomPosition();
					logger.debug("Needed " + (tryAgain + 1) + " trial to put ships on home field." + this.toString());
					tryAgain = maxTrial;
				} catch (IllegalArgumentException ex) {
					field = new ArrayList<List<Cell>>();
					setUninitializedCells(new Coordinate(gameConfiguration.height - 1, gameConfiguration.width - 1),
							getDefaultFillingType());
					tryAgain++;
				}
			}
		}
	}

	private void fillFieldWithShipsFromFileOnRandomPosition() {
		logger.debug("Field: " + toString());
		List<Ship> ships = ShipFactory.createShipsFromFile();
		for (Ship ship : ships) {
			Coordinate shipMaxCoordinate = ship.getMaxCoordinate();
			boolean successfulPlacement = false;
			while (!successfulPlacement) {
				Coordinate coordinate = getRandomCoordinate(shipMaxCoordinate);
				logger.debug("Trying to place ship: " + ship.toString() + " on Coordinate: " + coordinate
						+ "on Battlefield: " + this.toString());
				if (!isShipOutOfBounds(coordinate, shipMaxCoordinate)
						&& isFieldsOverwritable(coordinate, shipMaxCoordinate)) {
					addFieldToPosition(ship.getField(), coordinate);
					successfulPlacement = true;
					logger.debug("Successful placement of ship: " + ship.toString() + " on Coordinate: " + coordinate
							+ "on Battlefield: " + this.toString());
				} else {
					logger.debug("Unsuccessful placement of ship: " + ship.toString() + " on Coordinate: " + coordinate
							+ "on Battlefield: " + this.toString());
				}
			}
		}
	}

	private boolean isFieldsOverwritable(Coordinate coordinate, Coordinate shipMaxCoordinate) {
		for (int i = 0; i < shipMaxCoordinate.getY(); i++) {
			for (int j = 0; j < shipMaxCoordinate.getX(); j++) {
				Cell actualCell = getCell(new Coordinate(coordinate.getY() + i, coordinate.getX() + j));
				if (!isCellOverwritable(actualCell)) {
					return (false);
				}
			}
		}
		return true;
	}

	private boolean isCellOverwritable(Cell actualCell) {
		return actualCell != Cell.SHIP_PART && actualCell != Cell.DENIED;
	}

	private boolean isShipOutOfBounds(Coordinate coordinate, Coordinate shipMaxCoordinate) {
		return getMaxCoordinate().getX() - shipMaxCoordinate.getX() - coordinate.getX() < 0
				|| getMaxCoordinate().getY() - shipMaxCoordinate.getY() - coordinate.getY() < 0;
	}

	private Coordinate getRandomCoordinate(Coordinate shipMaxCoordinate) {
		List<Coordinate> possiblePlaces = new ArrayList<Coordinate>();
		for (int i = 0; i < getMaxCoordinate().getY() - shipMaxCoordinate.getY(); i++) {
			for (int j = 0; j < getMaxCoordinate().getX() - shipMaxCoordinate.getX(); j++) {
				Coordinate possibleCoordinate = new Coordinate(i, j);
				Cell cell = getCell(possibleCoordinate);
				if (cell == Cell.EMPTY) {
					if (isFieldsOverwritable(possibleCoordinate, shipMaxCoordinate)) {
						possiblePlaces.add(possibleCoordinate);
					}
				}
			}
		}
		return possiblePlaces.get(Utility.RANDOM.nextInt(possiblePlaces.size()));
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
