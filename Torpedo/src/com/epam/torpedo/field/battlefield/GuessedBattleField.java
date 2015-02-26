package com.epam.torpedo.field.battlefield;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.AbstractField;
import com.epam.torpedo.field.FieldType;

public class GuessedBattleField extends AbstractField {

	@Override
	protected void fillField() {
		fillField(FieldType.UNKNOWN, GameConfiguration.DEFAULT_BATTLE_FIELD_SIDE_LENGTH);
	}

}
