package com.epam.torpedo.strategies;

import java.util.NavigableMap;
import java.util.NoSuchElementException;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;

public class ShootEveryCellOneByOne extends Strategy {

	public ShootEveryCellOneByOne(GameConfiguration gameConfiguration) {
		super(gameConfiguration);
	}

	public Coordinate getNextAttackingCoordinate(NavigableMap<Coordinate, Cell> attackHistory) {
		Coordinate nextAttackingCoordinate;
		try {
			Coordinate lastAttackedCoordinate = attackHistory.lastKey();
			nextAttackingCoordinate = incrementCoordinate(lastAttackedCoordinate);
		} catch (NoSuchElementException ex) {
			nextAttackingCoordinate = new Coordinate(0, 0);
		}
		return nextAttackingCoordinate;
	}

	private Coordinate incrementCoordinate(Coordinate lastAttackedCoordinate) {
		validateCoordinateForIncrementation(lastAttackedCoordinate);

		int xCoordinate = lastAttackedCoordinate.getX() + 1;
		int yCoordinate = lastAttackedCoordinate.getY();

		if (xCoordinate >= gameConfiguration.width) {
			xCoordinate = 0;
			yCoordinate++;
		}
		return new Coordinate(yCoordinate, xCoordinate);
	}

	private void validateCoordinateForIncrementation(Coordinate lastAttackedCoordinate) {
		if (lastAttackedCoordinate.equals(new Coordinate(gameConfiguration.height - 1, gameConfiguration.width - 1))) {
			throw new IllegalArgumentException("Cannot give new Coordinate, because all has been used.");
		}
	}
}
