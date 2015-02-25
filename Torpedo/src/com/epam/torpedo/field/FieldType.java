package com.epam.torpedo.field;

public enum FieldType {
	EMPTY("."), SHIP_PART("x"), DENIED("-"),
	UNKNOWN("."), HIT("x"), MISSED("-");
	
	private final String readable; 
	
	FieldType(String readable) {
		this.readable = readable;
	}
	
	String getReadable() {
		return readable;
	}
}
