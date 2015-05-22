package com.epam.torpedo.field;

public class VirtualField implements Field {
	protected Coordinate maxCoordinate = new Coordinate(0, 0);

	@Override
	public Coordinate getMaxCoordinate() {
		return maxCoordinate;
	}

	public void setMaxCoordinate(Coordinate maxCoordinate) {
		this.maxCoordinate = maxCoordinate;
	}
	
	public boolean isCoordinateOutOfBounds(Coordinate coordinate) {
		return (maxCoordinate.getY() < coordinate.getY() || maxCoordinate.getX() < coordinate.getX()
				|| coordinate.getY() < 0 || coordinate.getX() < 0);
	}
}
