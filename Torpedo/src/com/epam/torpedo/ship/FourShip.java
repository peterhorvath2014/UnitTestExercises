package com.epam.torpedo.ship;

import com.epam.torpedo.field.FieldType;

public class FourShip extends Ship {
	public void fillShip() {
		matrix = new FieldType[6][3];
		// bottom line
		matrix[0][0] = FieldType.EMPTY;
		matrix[1][0] = FieldType.DENIED;
		matrix[2][0] = FieldType.DENIED;
		matrix[3][0] = FieldType.DENIED;
		matrix[4][0] = FieldType.DENIED;
		matrix[5][0] = FieldType.EMPTY;

		// middle line
		matrix[0][1] = FieldType.DENIED;
		matrix[1][1] = FieldType.SHIP_PART;
		matrix[2][1] = FieldType.SHIP_PART;
		matrix[3][1] = FieldType.SHIP_PART;
		matrix[4][1] = FieldType.SHIP_PART;
		matrix[5][1] = FieldType.DENIED;

		// top line
		matrix[0][2] = FieldType.EMPTY;
		matrix[1][2] = FieldType.DENIED;
		matrix[2][2] = FieldType.DENIED;
		matrix[3][2] = FieldType.DENIED;
		matrix[4][2] = FieldType.DENIED;
		matrix[5][2] = FieldType.EMPTY;
	}
}
