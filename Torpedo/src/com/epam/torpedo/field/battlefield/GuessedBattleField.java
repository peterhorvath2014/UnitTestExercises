package com.epam.torpedo.field.battlefield;

import com.epam.torpedo.field.AbstractField;
import com.epam.torpedo.field.FieldType;
import com.epam.torpedo.field.config.GameConfiguration;

public class GuessedBattleField extends AbstractField {

	@Override
	protected void fillField() {
		fillField(FieldType.UNKNOWN, GameConfiguration.DEFAULT_BATTLE_FIELD_SIDE_LENGTH);
	}

}
