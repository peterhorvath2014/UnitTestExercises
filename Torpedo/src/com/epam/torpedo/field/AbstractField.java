package com.epam.torpedo.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.epam.torpedo.field.ship.Ship;

public abstract class AbstractField {

	protected List<List<FieldType>> field;

	protected static Random random = new Random();

	protected int numberOfLiveShipParts;

	public AbstractField() {
		field = new ArrayList<List<FieldType>>();
	}

	public AbstractField(int numberOfLiveShipParts) {
		this();
		this.numberOfLiveShipParts = numberOfLiveShipParts;
		fillField();
	}

	protected abstract void fillField();

	protected void fillField(FieldType type, int sideLength) {
		for (int i = 0; i < sideLength; i++) {
			List<FieldType> row = new ArrayList<FieldType>();
			for (int j = 0; j < sideLength; j++) {
				row.add(type);
			}
			field.add(row);
		}
	}

	public int getNumberOfLiveShipParts() {
		return numberOfLiveShipParts;
	}

	public int getSideLengthX() {
		if (field.get(0).isEmpty()) {

		}
		return field.size();
	}

	public int getSideLengthY() {
		return field.size();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		if (field.size() == 0) {
			sb.append("Empty field!");
		} else {
			for (List<FieldType> row : field) {
				for (FieldType element : row) {
					sb.append(element.getReadable() + " ");
				}
				sb.append("\n");
			}
		}
		sb.append("\n");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + numberOfLiveShipParts;
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
		AbstractField other = (AbstractField) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (numberOfLiveShipParts != other.numberOfLiveShipParts)
			return false;
		return true;
	}

	protected int count(FieldType type) {
		int count = 0;
		for (List<FieldType> row : field) {
			for (FieldType element : row) {
				if (element == type) {
					count++;
				}
			}
		}
		return count;
	}

	public FieldType getCellFieldType(Coordinate coordinate) {
		if ((field.size() < 1) || (coordinate.getY() > getFieldMaxYCoordinate())
				|| (coordinate.getX() > getFieldMaxXCoordinate() - 1)) {
			throw new IllegalArgumentException("Coordinate is out of bounds");
		}
		return field.get(coordinate.getY()).get(coordinate.getX());
	}

	public void setCellFieldType(Coordinate coordinate, FieldType type) {
		setUninitializedCells(coordinate, getDefaultFillingType());
		field.get(coordinate.getY()).set(coordinate.getX(), type);
	}

	protected abstract FieldType getDefaultFillingType();

	public void setUninitializedCells(Coordinate coordinate, FieldType defaultFillingFieldType) {
		// the field size is dynamic, so if we want to set a cell, which is out
		// of bounds, then we increasing the size of the field, and fill it with
		// the default FieldType
		for (int i = 0; i <= coordinate.getY(); i++) {
			if (isFieldHasThisRowIndex(coordinate)) {
				field.add(new ArrayList<FieldType>());
			}
			while (isRowHasThisColumnIndex(i, coordinate)) {
				field.get(i).add(defaultFillingFieldType);
			}
		}
	}

	private int getFieldMaxYCoordinate() {
		return field.size() - 1;
	}

	private int getFieldMaxXCoordinate() {
		if (field.get(0) == null) {
			return -1;
		}
		return field.get(0).size() - 1;
	}

	private boolean isRowHasThisColumnIndex(int row, Coordinate coordinate) {
		return field.get(row).size() <= coordinate.getX();
	}

	private boolean isFieldHasThisRowIndex(Coordinate coordinate) {
		return field.size() <= coordinate.getY();
	}

	public boolean isDone() {
		return count(FieldType.HIT) == numberOfLiveShipParts;
	}

	public void addFieldToPosition(List<List<FieldType>> field, Coordinate coordinate) {
		int x = coordinate.getX();
		int y = coordinate.getY();

		for (List<FieldType> row : field) {
			for (FieldType cell : row) {
				this.setCellFieldType(new Coordinate(y, x), cell);
				x++;
			}
			x = coordinate.getX();
			y++;
		}
	}

	public void addLine(String[] cells) {
		List<FieldType> row = new ArrayList<FieldType>();
		for (String cell : cells) {
			row.add(FieldType.getFieldTypeByReadable(cell));
		}
		field.add(row);
	}

	public Ship generateDeniedFields() {
		this.autoAddEmptyBorder();
		return null;
	}

	public void autoAddEmptyBorder() {
		if (!field.isEmpty()) {
			if (!isTopLineEmpty()) {
				addEmptyTopLine();
			}
			if (!isLeftLineEmpty()) {
				addEmptyLeftLine();
			}
		}
	}

	private void addEmptyLeftLine() {
		AbstractField newShip = new Ship();
		newShip.setUninitializedCells(new Coordinate(getFieldMaxYCoordinate(), 0), FieldType.EMPTY);
		newShip.addFieldToPosition(field, new Coordinate(0, 1));
		field = newShip.getField();
	}

	private boolean isLeftLineEmpty() {
		boolean result = true;
		for (List<FieldType> row : field) {
			if (row.get(0) == FieldType.SHIP_PART) {
				result = false;
			}
		}
		return result;
	}

	private void addEmptyTopLine() {
		AbstractField newShip = new Ship();
		newShip.setUninitializedCells(new Coordinate(0, getFieldMaxXCoordinate()), FieldType.EMPTY);
		newShip.addFieldToPosition(field, new Coordinate(1, 0));
		field = newShip.getField();
	}

	public List<List<FieldType>> getField() {
		return field;
	}

	private boolean isTopLineEmpty() {
		return !field.get(0).contains(FieldType.SHIP_PART);
	}
}
