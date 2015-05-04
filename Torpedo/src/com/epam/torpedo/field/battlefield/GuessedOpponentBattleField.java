package com.epam.torpedo.field.battlefield;

import java.util.LinkedList;

import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.VirtualField;

public class GuessedOpponentBattleField extends VirtualField implements AttackHistoryHolder {
	private LinkedList<Coordinate> attackHistory = new LinkedList<Coordinate>();

	@Override
	public LinkedList<Coordinate> getAttackHistory() {
		return attackHistory;
	}

	@Override
	public void addAttackHistory(Coordinate coordinate) {
		attackHistory.add(coordinate);
	}
}
