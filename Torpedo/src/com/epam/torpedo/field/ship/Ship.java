package com.epam.torpedo.field.ship;

import com.epam.torpedo.field.Field;
import com.epam.torpedo.field.Cell;

public class Ship extends Field {

	@Override
	protected void fillField() {
		// We don't know how big is the ship which we read from the file, so we
		// leave the "field" empty
	}

	@Override
	protected Cell getDefaultFillingType() {
		return Cell.EMPTY;
	}

}
