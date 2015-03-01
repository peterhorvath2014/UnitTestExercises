package com.epam.torpedo.communication.gameapi;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;

public class EnemyAPI implements GameAPI {

	@Override
	public Cell shoot(Coordinate coordinate) {
		return null;
	}

	@Override
	public GameConfiguration hello() {
		//TODO check if result is null
		return new GameConfiguration(10, 10);
	}

}
