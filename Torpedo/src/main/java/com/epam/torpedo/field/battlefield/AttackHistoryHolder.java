package com.epam.torpedo.field.battlefield;

import java.util.NavigableMap;

import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;

public interface AttackHistoryHolder {

	public NavigableMap<Coordinate, Cell> getAttackHistory();

	public void addAttackHistory(Coordinate coordinate, Cell cell);

}