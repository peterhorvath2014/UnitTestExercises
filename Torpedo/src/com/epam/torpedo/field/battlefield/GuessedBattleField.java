package com.epam.torpedo.field.battlefield;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Field;
import com.epam.torpedo.field.Cell;

public class GuessedBattleField extends Field {

	@Override
	protected void fillField() {
		fillField(Cell.UNKNOWN, GameConfiguration.DEFAULT_BATTLE_FIELD_SIDE_LENGTH);
	}

	@Override
	protected Cell getDefaultFillingType() {
		return Cell.UNKNOWN;
	}

}
