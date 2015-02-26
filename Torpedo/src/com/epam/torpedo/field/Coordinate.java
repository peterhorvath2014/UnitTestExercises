package com.epam.torpedo.field;

public class Coordinate {
	private int x;
	private int y;

	public Coordinate(int y, int x) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Coordinate [y=" + y + ", x=" + x + "]";
	}

}
