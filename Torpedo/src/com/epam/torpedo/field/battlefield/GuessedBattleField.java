package com.epam.torpedo.field.battlefield;

import com.epam.torpedo.field.AbstractField;
import com.epam.torpedo.field.FieldType;

public class GuessedBattleField extends AbstractField {

	@Override
	protected void fillField() {
		fillField(FieldType.UNKNOWN);
	}

}
