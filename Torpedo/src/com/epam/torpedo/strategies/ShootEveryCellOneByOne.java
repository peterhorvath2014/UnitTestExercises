package com.epam.torpedo.strategies;

import java.util.LinkedList;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Coordinate;

public class ShootEveryCellOneByOne extends Strategy {

	public ShootEveryCellOneByOne(GameConfiguration gameConfiguration) {
		super(gameConfiguration);
	}

	public Coordinate getNextAttackingCoordinate(LinkedList<Coordinate> attackHistory) {
		Coordinate nextAttackingCoordinate;
		Coordinate lastAttackedCoordinate = attackHistory.peekLast();
		if (lastAttackedCoordinate == null) {
			nextAttackingCoordinate = new Coordinate(0, 0);
		} else {
			nextAttackingCoordinate = incrementCoordinate(lastAttackedCoordinate);
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

	private void validateCoordinateForIncrementation(
			Coordinate lastAttackedCoordinate) {
		if (lastAttackedCoordinate.equals(new Coordinate(
				gameConfiguration.height - 1, gameConfiguration.width - 1))) {
			throw new IllegalArgumentException(
					"Cannot give new Coordinate, because all has been used.");
		}
	}
}
