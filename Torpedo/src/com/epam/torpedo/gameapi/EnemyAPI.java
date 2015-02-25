package com.epam.torpedo.gameapi;

import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.util.Utility;

public class EnemyAPI implements GameAPI {

	@Override
	public boolean isShipPart(Coordinate coordinate) {
		//TODO reach enemy
		return Utility.RANDOM.nextBoolean();
	}

}
