package com.epam.torpedo.strategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.map.LinkedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.battlefield.GuessedOpponentBattleField;
import com.epam.torpedo.util.Utility;

public class ShootRandomThenAround extends Strategy {
	private static final Logger logger = LogManager.getLogger();

	private boolean isShootingModeRandom = true;

	public ShootRandomThenAround(GameConfiguration gameConfiguration) {
		super(gameConfiguration);
	}

	public Coordinate getNextAttackingCoordinate(GuessedOpponentBattleField guessedOpponentBattleField) {
		Coordinate nextAttackingCoordinate = null;
		Coordinate lastCoordinate = null;
		try {
			lastCoordinate = guessedOpponentBattleField.getAttackHistory().lastKey();
			Cell lastCell = guessedOpponentBattleField.getAttackHistory().get(lastCoordinate);
			logger.debug("lastCoordinate: " + lastCoordinate);
			logger.debug("lastCell: " + lastCell);
			logger.debug("isShootingModeRandom: " + isShootingModeRandom);
			if ((lastCell.equals(Cell.HIT) || (!isShootingModeRandom) && (!lastCell.equals(Cell.SUNK)))) {
				logger.debug("Specific killing mode.");
				isShootingModeRandom = false;
				nextAttackingCoordinate = getNextSpecificCoordinate(guessedOpponentBattleField);
			} else {
				logger.debug("Random shooting mode.");
				isShootingModeRandom = true;
				nextAttackingCoordinate = getNexRandomCoordinate(guessedOpponentBattleField.getAttackHistory());
			}
		} catch (NoSuchElementException ex) {
			logger.debug("Random shooting mode.");
			nextAttackingCoordinate = getNexRandomCoordinate(guessedOpponentBattleField.getAttackHistory());
		}
		return nextAttackingCoordinate;
	}

	private Coordinate getNextSpecificCoordinate(GuessedOpponentBattleField guessedOpponentBattleField) {
		Coordinate nextAttackingCoordinate = null;
		Coordinate lastHitCoordinate = null;
		Coordinate lastKey = guessedOpponentBattleField.getAttackHistory().lastKey();
		while (lastHitCoordinate == null) {
			Cell cell = guessedOpponentBattleField.getAttackHistory().get(lastKey);
			if (cell.equals(Cell.HIT)) {
				lastHitCoordinate = lastKey;
			}
			lastKey = guessedOpponentBattleField.getAttackHistory().previousKey(lastKey);
		}
		logger.debug("lastHitCoordinate: " + lastHitCoordinate);
		nextAttackingCoordinate = getNextNeighborAttackingCoordinate(guessedOpponentBattleField, lastHitCoordinate);
		nextAttackingCoordinate = getNeighborsNeighborAttackingCoordinate(guessedOpponentBattleField,
				nextAttackingCoordinate, lastHitCoordinate);
		validateGettingNextAttackingCoordinateSuccessful(nextAttackingCoordinate, lastHitCoordinate);
		return nextAttackingCoordinate;
	}

	private void validateGettingNextAttackingCoordinateSuccessful(Coordinate nextAttackingCoordinate,
			Coordinate lastHitCoordinate) {
		if (nextAttackingCoordinate == null) {
			String errorMessage = "Couldn't find any unfired neighbor cell. Y = " + lastHitCoordinate.getY() + " X = " + lastHitCoordinate.getY();
			logger.debug(errorMessage);
			throw new IllegalStateException(errorMessage);
		}
	}

	private Coordinate getNeighborsNeighborAttackingCoordinate(GuessedOpponentBattleField guessedOpponentBattleField,
			Coordinate nextAttackingCoordinate, Coordinate lastHitCoordinate) {
		if (nextAttackingCoordinate == null) {
			logger.debug(guessedOpponentBattleField.toString());
			HashSet<Coordinate> allNeighborHitCells = getAllNeighborHitCells(guessedOpponentBattleField,
					lastHitCoordinate, new HashSet<Coordinate>());
			Iterator<Coordinate> iterator = allNeighborHitCells.iterator();
			while (iterator.hasNext() && nextAttackingCoordinate == null) {
				Coordinate coordinate = iterator.next();
				logger.debug("while: " + coordinate);
				nextAttackingCoordinate = getNextNeighborAttackingCoordinate(guessedOpponentBattleField, coordinate);
			}
		}
		return nextAttackingCoordinate;
	}

	private HashSet<Coordinate> getAllNeighborHitCells(GuessedOpponentBattleField guessedOpponentBattleField,
			Coordinate coordinate, HashSet<Coordinate> allNeighborHitCells) {
		logger.debug("getAllNeighborHitCells: " + coordinate);

		// recursive call to all surrounding cell which has HIT on it
		for (int i = coordinate.getY() - 1; i <= coordinate.getY() + 1; i++) {
			for (int j = coordinate.getX() - 1; j <= coordinate.getX() + 1; j++) {
				Coordinate connectedCoordinate = new Coordinate(i, j);
				logger.debug("connectedCoordinate: " + connectedCoordinate);
				if (!guessedOpponentBattleField.isCoordinateOutOfBounds(connectedCoordinate)) {
					if (guessedOpponentBattleField.isHit(connectedCoordinate)) {
						if (!allNeighborHitCells.contains(connectedCoordinate)) {
							allNeighborHitCells.add(connectedCoordinate);
							allNeighborHitCells.addAll(getAllNeighborHitCells(guessedOpponentBattleField,
									connectedCoordinate, allNeighborHitCells));
						}
					}
				}
			}
		}

		return allNeighborHitCells;
	}

	private Coordinate getNextNeighborAttackingCoordinate(GuessedOpponentBattleField guessedOpponentBattleField,
			Coordinate lastHitCoordinate) {
		int i = -1, j = -1;
		Coordinate nextAttackingCoordinate = null;
		while (nextAttackingCoordinate == null && j < 2) {
			int possibleY = lastHitCoordinate.getY() + i;
			int possibleX = lastHitCoordinate.getX() + j;
			if (!isOutOfBounds(possibleY, possibleX)
					&& (!guessedOpponentBattleField.getAttackHistory()
							.containsKey(new Coordinate(possibleY, possibleX)))) {
				nextAttackingCoordinate = new Coordinate(possibleY, possibleX);
			} else {
				i++;
				if (i == 2) {
					i = -1;
					j++;
				}
			}
		}
		return nextAttackingCoordinate;
	}

	private boolean isOutOfBounds(int possibleY, int possibleX) {
		return !(possibleY >= 0 && possibleY < gameConfiguration.width && possibleX >= 0 && possibleX < gameConfiguration.height);
	}

	private Coordinate getNexRandomCoordinate(LinkedMap<Coordinate, Cell> attackHistory) {
		Coordinate nextAttackingCoordinate;
		int randomY = getRandomYCoordinate(attackHistory);
		int randomX = getRandomXCoordinate(attackHistory, randomY);
		nextAttackingCoordinate = new Coordinate(randomY, randomX);
		return nextAttackingCoordinate;
	}

	private int getRandomXCoordinate(LinkedMap<Coordinate, Cell> attackHistory, int randomY) {
		List<Integer> possibleXCoordinates = new ArrayList<Integer>();
		for (int i = 0; i < gameConfiguration.width; i++) {
			if (!attackHistory.keySet().contains(new Coordinate(randomY, i))) {
				possibleXCoordinates.add(i);
			}
		}
		int randomX = possibleXCoordinates.get(Utility.RANDOM.nextInt(possibleXCoordinates.size()));
		return randomX;
	}

	private int getRandomYCoordinate(LinkedMap<Coordinate, Cell> attackHistory) {
		List<Integer> possibleYCoordinates = new ArrayList<Integer>();
		for (int i = 0; i < gameConfiguration.height; i++) {
			int numberOfCoordinatesInARow = 0;
			for (Coordinate coordinate : attackHistory.keySet()) {
				if (coordinate.getY() == i) {
					numberOfCoordinatesInARow++;
				}
			}
			if (numberOfCoordinatesInARow < gameConfiguration.width) {
				possibleYCoordinates.add(i);
			}
		}
		validatePossibleYCoordinates(possibleYCoordinates);
		int randomY = possibleYCoordinates.get(Utility.RANDOM.nextInt(possibleYCoordinates.size()));
		return randomY;
	}

	private void validatePossibleYCoordinates(List<Integer> possibleYCoordinates) {
		if (possibleYCoordinates.isEmpty()) {
			throw new IllegalArgumentException("Cannot give new Coordinate, because all has been used.");
		}
	}
}
