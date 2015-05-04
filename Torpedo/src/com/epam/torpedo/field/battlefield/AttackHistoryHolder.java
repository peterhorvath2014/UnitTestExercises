package com.epam.torpedo.field.battlefield;

import java.util.LinkedList;

import com.epam.torpedo.field.Coordinate;

public interface AttackHistoryHolder {

	public LinkedList<Coordinate> getAttackHistory();

	public void addAttackHistory(Coordinate coordinate);

}