package com.epam.torpedo.field.ship;

import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Field;

public class Ship extends Field {

	public Ship() {
		super();
	}
	
	@Override
	protected Cell getDefaultFillingType() {
		return Cell.EMPTY;
	}

}
