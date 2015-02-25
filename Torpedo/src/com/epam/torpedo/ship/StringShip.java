package com.epam.torpedo.ship;

public class StringShip extends Ship {
	private static final char EMPTY = '.';
	private static final char SHIP = 'x';

	public StringShip() {
		// TODO Auto-generated constructor stub
	}

	// copy contructor
	public StringShip(StringShip stringShip) {
		this.matrix = stringShip.matrix;
	}

	@Override
	public void fillShip() {
		// TODO Auto-generated method stub
	}

	public void fillShip(String shipInLines) {
		// TODO should create ship matrix from String
	}

}
