package com.epam.torpedo.field.ship;

import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.RealField;

public class Ship extends RealField {

	public Ship() {
		super();
	}
	
	@Override
	protected Cell getDefaultFillingType() {
		return Cell.EMPTY;
	}

}
