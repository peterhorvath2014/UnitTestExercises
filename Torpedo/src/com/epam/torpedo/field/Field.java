package com.epam.torpedo.field;

public interface Field {

	public abstract int getNumberOfLiveShipParts();

	public abstract int getSideLengthX();

	public abstract int getSideLengthY();

	public abstract String toString();

	public abstract int hashCode();

	public abstract boolean equals(Object obj);

	public abstract Cell getCell(Coordinate coordinate);

	public abstract void setCell(Coordinate coordinate, Cell type);

	public abstract boolean isDone(int numberOfLiveShipParts);

	public abstract boolean isCellOverwritable(Cell newCell, Coordinate actualCoordinate);

	public abstract void addLine(String[] cells);

	public abstract boolean isShipPart(Coordinate coordinate);

	public abstract boolean isHit(Coordinate coordinate);

	public abstract void changeAllConnectedHitToSunk(Coordinate coordinate);

}