package com.epam.torpedo.ship;

import com.epam.torpedo.field.FieldType;

public abstract class Ship {
	protected FieldType[][] matrix;

	public FieldType[][] getMatrix() {
		return matrix;
	}
	
	public abstract void fillShip();
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				char toPrint = '-';
				if (matrix[i][j] == FieldType.SHIP_PART) {
					toPrint = '+';
				}
				sb.append(toPrint);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
