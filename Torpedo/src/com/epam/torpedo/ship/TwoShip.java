package com.epam.torpedo.ship;

import com.epam.torpedo.FieldType;

public class TwoShip extends Ship {
	public void fillShip() {
		matrix = new FieldType[4][3];
		// bottom line
		matrix[0][0] = FieldType.EMPTY;
		matrix[1][0] = FieldType.DENIED;
		matrix[2][0] = FieldType.DENIED;
		matrix[3][0] = FieldType.EMPTY;

		// middle line
		matrix[0][1] = FieldType.DENIED;
		matrix[1][1] = FieldType.SHIP_PART;
		matrix[2][1] = FieldType.SHIP_PART;
		matrix[3][1] = FieldType.DENIED;

		// top line
		matrix[0][2] = FieldType.EMPTY;
		matrix[1][2] = FieldType.DENIED;
		matrix[2][2] = FieldType.DENIED;
		matrix[3][2] = FieldType.EMPTY;
	}
}
