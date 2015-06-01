package com.epam.torpedo.strategies;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.battlefield.GuessedOpponentBattleField;

public abstract class Strategy {

	GameConfiguration gameConfiguration;
	
	public Strategy(GameConfiguration gameConfiguration) {
		this.gameConfiguration = gameConfiguration;
	}

	public abstract Coordinate getNextAttackingCoordinate(GuessedOpponentBattleField guessedopponentBattleField);
}