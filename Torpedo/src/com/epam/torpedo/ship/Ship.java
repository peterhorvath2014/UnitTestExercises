package com.epam.torpedo.ship;

import com.epam.torpedo.field.FieldType;

public abstract class Ship {
	protected FieldType[][] matrix;

	public FieldType[][] getMatrix() {
		return matrix;
	}
	
	public abstract void fillShip();
}
