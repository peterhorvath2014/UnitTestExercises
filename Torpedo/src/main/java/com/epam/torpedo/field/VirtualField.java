package com.epam.torpedo.field;

import java.util.NavigableMap;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class VirtualField implements Field {
	private static final Logger logger = LogManager.getLogger();
	protected Coordinate maxCoordinate = new Coordinate(0, 0);
	protected NavigableMap<Coordinate, Cell> attackHistory = new TreeMap<Coordinate, Cell>();

	public NavigableMap<Coordinate, Cell> getAttackHistory() {
		return attackHistory;
	}

	public void addAttackHistory(Coordinate coordinate, Cell cell) {
		attackHistory.put(coordinate, cell);
		logger.debug(this.toString());
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
}
