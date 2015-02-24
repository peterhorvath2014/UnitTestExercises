package com.epam.torpedo.ship;

public class ShipFactory {

	public Ship createOneShip() {
		OneShip ship = new OneShip();
		ship.fillShip();
		return ship;
	}

	public Ship createTwoShip() {
		TwoShip ship = new TwoShip();
		ship.fillShip();
		return ship;
	}

	public Ship createThreeShip() {
		ThreeShip ship = new ThreeShip();
		ship.fillShip();
		return ship;
	}

	public Ship createFourShip() {
		FourShip ship = new FourShip();
		ship.fillShip();
		return ship;
	}

	public Ship createTetrisShip() {
		TetrisShip ship = new TetrisShip();
		ship.fillShip();
		return ship;
	}

}
