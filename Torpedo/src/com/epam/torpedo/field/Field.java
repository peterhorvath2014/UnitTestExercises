package com.epam.torpedo.field;


public interface Field {

	public int getNumberOfLiveShipParts();
	
	public Coordinate getMaxCoordinate();

	public String toString();

	public int hashCode();

	public boolean equals(Object obj);

	public Cell getCell(Coordinate coordinate);

	public void setCell(Coordinate coordinate, Cell type);

	public boolean isDone(int numberOfLiveShipParts);

	public boolean isCellOverwritable(Cell newCell, Coordinate actualCoordinate);

	public void addLine(String[] cells);

	public boolean isShipPart(Coordinate coordinate);

	public boolean isHit(Coordinate coordinate);

	public void changeAllConnectedHitToSunk(Coordinate coordinate);
	
	public void printField(); 

}