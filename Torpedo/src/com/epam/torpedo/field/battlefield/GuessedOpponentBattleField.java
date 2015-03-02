package com.epam.torpedo.field.battlefield;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Field;

public class GuessedOpponentBattleField extends Field {

	public GuessedOpponentBattleField() {
		super();
	}
	
	public GuessedOpponentBattleField(GameConfiguration gameConfiguration) {
		super(gameConfiguration);
	}
	
	@Override
	protected Cell getDefaultFillingType() {
		return Cell.UNKNOWN;
	}

}
