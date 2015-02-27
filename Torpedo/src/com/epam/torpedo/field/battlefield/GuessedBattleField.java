package com.epam.torpedo.field.battlefield;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Field;
import com.epam.torpedo.field.FieldType;

public class GuessedBattleField extends Field {

	@Override
	protected void fillField() {
		fillField(FieldType.UNKNOWN, GameConfiguration.DEFAULT_BATTLE_FIELD_SIDE_LENGTH);
	}

	@Override
	protected FieldType getDefaultFillingType() {
		return FieldType.UNKNOWN;
	}

}
