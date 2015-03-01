package com.epam.torpedo.field;

public enum Cell {
	EMPTY("."), SHIP_PART("x"), DENIED("-"), UNKNOWN("U"), HIT("H"), SUNK("S"), MISSED("M");

	private final String readable;

	Cell(String readable) {
		this.readable = readable;
	}

	String getReadable() {
		return readable;
	}

	public static Cell getFieldTypeByReadable(String readable) {
		switch (readable) {
		case ".":
			return EMPTY;
		case "x":
			return SHIP_PART;
		case "-":
			return DENIED;
		case "H":
			return HIT;
		case "S":
			return SUNK;
		case "M":
			return MISSED;
		default: 
			return UNKNOWN;
		}
	}
	
	public static Cell getFieldTypeByFieldName(String name) {
		switch (name) {
		case "HIT":
			return HIT;
		case "SUNK":
			return SUNK;
		case "MISSED":
			return MISSED;
		default: 
			return UNKNOWN;
		}
	}
}
