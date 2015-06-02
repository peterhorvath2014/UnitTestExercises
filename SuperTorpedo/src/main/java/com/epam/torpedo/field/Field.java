package com.epam.torpedo.field;

public interface Field {
	public Coordinate getMaxCoordinate();

	public String toString();

	public int hashCode();

	public boolean equals(Object obj);

	public boolean isCoordinateOutOfBounds(Coordinate coordinate);
}