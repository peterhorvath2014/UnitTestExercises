package com.epam.torpedo.field;

public enum FieldType {
	EMPTY("."), SHIP_PART("x"), DENIED("-"), UNKNOWN("U"), HIT("H"), MISSED("M");

	private final String readable;

	FieldType(String readable) {
		this.readable = readable;
	}

	String getReadable() {
		return readable;
	}

	public static FieldType getFieldTypeByReadable(String readable) {
		switch (readable) {
		case ".":
			return EMPTY;
		case "x":
			return SHIP_PART;
		case "-":
			return DENIED;
		case "H":
			return HIT;
		case "M":
			return MISSED;
		default: 
			return UNKNOWN;
		}
	}
}
