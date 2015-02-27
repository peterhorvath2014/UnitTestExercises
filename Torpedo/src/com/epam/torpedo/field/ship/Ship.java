package com.epam.torpedo.field.ship;

import com.epam.torpedo.field.Field;
import com.epam.torpedo.field.FieldType;

public class Ship extends Field {

	@Override
	protected void fillField() {
		// We don't know how big is the ship which we read from the file, so we
		// leave the "field" empty
	}

	@Override
	protected FieldType getDefaultFillingType() {
		return FieldType.EMPTY;
	}

}
