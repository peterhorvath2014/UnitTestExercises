package com.epam.torpedo;

public interface Matrix {
	public int countLiveShips();
	public boolean isFound(Point point);
	public void foundShip(Point nextPoint);
	public void fillRandomShips();
}
