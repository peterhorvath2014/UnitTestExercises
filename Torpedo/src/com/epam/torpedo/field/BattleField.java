package com.epam.torpedo.field;


public interface BattleField {
	public boolean isFound(FieldCoordinate point);
	public void foundShip(FieldCoordinate point);
	public void fillRandomShips();
}
