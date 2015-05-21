package com.epam.torpedo.field;

import java.util.LinkedList;
import java.util.List;

import com.epam.torpedo.field.ship.Ship;

public class VirtualField implements Field {
	//private List<Ship> ships = new LinkedList<Ship>();
	protected Coordinate maxCoordinate = new Coordinate(0, 0);

	/*public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}*/


	@Override
	public int getNumberOfLiveShipParts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Coordinate getMaxCoordinate() {
		return maxCoordinate;
	}

	@Override
	public Cell getCell(Coordinate coordinate) {
		//TODO
		
		return null;
	}

	@Override
	public void setCell(Coordinate coordinate, Cell type) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDone(int numberOfLiveShipParts) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCellOverwritable(Cell newCell, Coordinate actualCoordinate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addLine(String[] cells) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isShipPart(Coordinate coordinate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHit(Coordinate coordinate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void changeAllConnectedHitToSunk(Coordinate coordinate) {
		// TODO Auto-generated method stub

	}

	public void setMaxCoordinate(Coordinate maxCoordinate) {
		this.maxCoordinate = maxCoordinate;
	}

}
