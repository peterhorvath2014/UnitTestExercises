package com.epam.torpedo.field;

import java.util.LinkedList;
import java.util.List;

import com.epam.torpedo.field.ship.Ship;

public class VirtualField implements Field {
	private List<Ship> ships = new LinkedList<Ship>();
	protected LinkedList<Coordinate> attackHistory;

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	public LinkedList<Coordinate> getAttackHistory() {
		return attackHistory;
	}

	public void setAttackHistory(LinkedList<Coordinate> attackHistory) {
		this.attackHistory = attackHistory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attackHistory == null) ? 0 : attackHistory.hashCode());
		result = prime * result + ((ships == null) ? 0 : ships.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VirtualField other = (VirtualField) obj;
		if (attackHistory == null) {
			if (other.attackHistory != null)
				return false;
		} else if (!attackHistory.equals(other.attackHistory))
			return false;
		if (ships == null) {
			if (other.ships != null)
				return false;
		} else if (!ships.equals(other.ships))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VirtualField [ships=" + ships + ", attackHistory=" + attackHistory + "]";
	}

	@Override
	public int getNumberOfLiveShipParts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSideLengthX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSideLengthY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cell getCell(Coordinate coordinate) {
		// TODO Auto-generated method stub
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

}
