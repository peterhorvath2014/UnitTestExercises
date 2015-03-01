package com.epam.torpedo.strategies;

import java.util.LinkedList;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Coordinate;

public abstract class Strategy {

	GameConfiguration gameConfiguration;
	
	public Strategy(GameConfiguration gameConfiguration) {
		this.gameConfiguration = gameConfiguration;
	}

	public abstract Coordinate getNextAttackingCoordinate(LinkedList<Coordinate> attackHistory);
}