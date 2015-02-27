package com.epam.torpedo.gameapi;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;

public interface GameAPI {
	public GameConfiguration hello();
	public Cell shoot(Coordinate coordinate);
}
