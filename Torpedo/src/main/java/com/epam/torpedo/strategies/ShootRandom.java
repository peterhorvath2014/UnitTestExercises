package com.epam.torpedo.strategies;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.map.LinkedMap;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.battlefield.GuessedOpponentBattleField;
import com.epam.torpedo.util.Utility;

public class ShootRandom extends Strategy {

	public ShootRandom(GameConfiguration gameConfiguration) {
		super(gameConfiguration);
	}

	public Coordinate getNextAttackingCoordinate(GuessedOpponentBattleField guessedOpponentBattleField) {
		int randomY = getRandomYCoordinate(guessedOpponentBattleField.getAttackHistory());
		int randomX = getRandomXCoordinate(guessedOpponentBattleField.getAttackHistory(), randomY);
		Coordinate nextAttackingCoordinate = new Coordinate(randomY, randomX);
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
