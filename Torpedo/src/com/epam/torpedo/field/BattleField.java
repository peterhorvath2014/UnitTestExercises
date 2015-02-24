package com.epam.torpedo.field;


public interface BattleField {
	public int countLiveShips();
	public boolean isFound(FieldCoordinate point);
	public void foundShip(FieldCoordinate nextPoint);
	public void fillRandomShips();
}
