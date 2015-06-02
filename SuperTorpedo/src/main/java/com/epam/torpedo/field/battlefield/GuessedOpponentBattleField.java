package com.epam.torpedo.field.battlefield;

import org.apache.commons.collections4.map.LinkedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.Field;

public class GuessedOpponentBattleField implements Field {
	private static final Logger logger = LogManager.getLogger();
	protected Coordinate maxCoordinate = new Coordinate(0, 0);
	protected LinkedMap<Coordinate, Cell> attackHistory = new LinkedMap<Coordinate, Cell>();

	public LinkedMap<Coordinate, Cell> getAttackHistory() {
		return attackHistory;
	}

	public void addAttackHistory(Coordinate coordinate, Cell cell) {
		attackHistory.put(coordinate, cell);
		if (cell.equals(Cell.HIT)) {
			logger.debug(this.toString());
		}
	}
	@Override
	public Coordinate getMaxCoordinate() {
		return maxCoordinate;
	}

	public void setMaxCoordinate(Coordinate maxCoordinate) {
		this.maxCoordinate = maxCoordinate;
	}

	public boolean isCoordinateOutOfBounds(Coordinate coordinate) {
		return (maxCoordinate.getY() < coordinate.getY() || maxCoordinate.getX() < coordinate.getX()
				|| coordinate.getY() < 0 || coordinate.getX() < 0);
	}
	
	public boolean isHit(Coordinate coordinate) {
		return getCell(coordinate) == Cell.HIT;
	}

	public Cell getCell(Coordinate coordinate) {
		Cell cell = attackHistory.get(coordinate);
		return cell;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("maxCoordinate: " + maxCoordinate);
		if (maxCoordinate.getX() <= 100) {
			sb.append("\n");
			for (int i = 0; i <= maxCoordinate.getX(); i++) {
				for (int j = 0; j <= maxCoordinate.getY(); j++) {
					Cell cell = attackHistory.get(new Coordinate(i, j));
					if (cell == null) {
						sb.append(".. ");
					} else {
						String displayString = cell.name().substring(0, 2);
						if (displayString.equals("MI")) {
							displayString = displayString.toLowerCase();
						}
						sb.append(displayString + " ");
					}
				}
				sb.append("\n");
			}
		} else {
			sb.append("Battlefield is too big(>100) to build string.");
		}
		return sb.toString();
	}

	public void changeAllConnectedHitToSunk(Coordinate coordinate) {
		addAttackHistory(coordinate, Cell.SUNK);
		// recursive call to all surrounding cell which has HIT on it
		for (int i = coordinate.getY() - 1; i <= coordinate.getY() + 1; i++) {
			for (int j = coordinate.getX() - 1; j <= coordinate.getX() + 1; j++) {
				Coordinate connectedCoordinate = new Coordinate(i, j);
				if (connectedCoordinate != null && !isCoordinateOutOfBounds(connectedCoordinate)) {
					if (isHit(connectedCoordinate)) {
						changeAllConnectedHitToSunk(connectedCoordinate);
					}
				}
			}
		}
	}

	public boolean isDone(int numberOfLiveShipParts) {
		return getNumberOfLiveShipParts() == numberOfLiveShipParts;
	}

	private int getNumberOfLiveShipParts() {
		int numberOfLiveShipParts = 0;
		for (Cell cell : attackHistory.values()) {
			if (cell == Cell.SUNK) {
				numberOfLiveShipParts++;
			}
		}
		return numberOfLiveShipParts;
	}
}
