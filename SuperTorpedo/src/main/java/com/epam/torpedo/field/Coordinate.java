package com.epam.torpedo.field;

public class Coordinate implements Comparable<Coordinate> {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

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

	@Override
	public int compareTo(Coordinate o) {
		int result = 0;
		if (this.y < o.y) {
			result = -1;
		} else if (this.y > o.y) {
			result = 1;
		} else {
			if (this.x < o.x) {
				result = -1;
			} else if (this.x > o.x) {
				result = 1;
			}
		}
		return result;
	}

}
