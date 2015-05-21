package com.epam.torpedo.field.battlefield;

import java.util.NavigableMap;
import java.util.TreeMap;

import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.VirtualField;

public class GuessedOpponentBattleField extends VirtualField implements AttackHistoryHolder {
	protected NavigableMap<Coordinate, Cell> attackHistory = new TreeMap<Coordinate, Cell>();

	@Override
	public NavigableMap<Coordinate, Cell> getAttackHistory() {
		return attackHistory;
	}

	@Override
	public void addAttackHistory(Coordinate coordinate, Cell cell) {
		attackHistory.put(coordinate, cell);
	}
	
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("maxCoordinate: " + maxCoordinate);
    sb.append("/n");
    for (int i = 0; i < maxCoordinate.getX(); i++) {
      for (int j = 0; j < maxCoordinate.getY(); j++) {
        Cell cell = attackHistory.get(new Coordinate(i,j));
        sb.append(cell);
      }
      sb.append("/n");
    }
    return sb.toString();
  }
}
