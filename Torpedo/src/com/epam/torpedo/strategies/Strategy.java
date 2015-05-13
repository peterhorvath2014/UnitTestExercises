package com.epam.torpedo.strategies;

import java.util.NavigableMap;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;

public abstract class Strategy {

	GameConfiguration gameConfiguration;
	
	public Strategy(GameConfiguration gameConfiguration) {
		this.gameConfiguration = gameConfiguration;
	}

	public abstract Coordinate getNextAttackingCoordinate(NavigableMap<Coordinate, Cell> attackHistory);
}