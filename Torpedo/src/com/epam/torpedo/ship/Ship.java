package com.epam.torpedo.ship;

import com.epam.torpedo.FieldType;

public abstract class Ship {
	protected FieldType[][] matrix;

	public FieldType[][] getMatrix() {
		return matrix;
	}
	
	public abstract void fillShip();
}
