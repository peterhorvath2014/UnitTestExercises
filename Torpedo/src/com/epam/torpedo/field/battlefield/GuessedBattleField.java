package com.epam.torpedo.field.battlefield;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Field;

public class GuessedBattleField extends Field {

	public GuessedBattleField() {
		super();
	}
	
	public GuessedBattleField(GameConfiguration gameConfiguration) {
		super(gameConfiguration);
	}
	
	@Override
	protected Cell getDefaultFillingType() {
		return Cell.UNKNOWN;
	}

}
