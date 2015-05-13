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
}
