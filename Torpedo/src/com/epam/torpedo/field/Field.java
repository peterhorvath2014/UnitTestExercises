package com.epam.torpedo.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.epam.torpedo.field.ship.Ship;

public abstract class Field {

	protected List<List<Cell>> field;

	protected static Random random = new Random();

	protected int numberOfLiveShipParts;

	public Field() {
		field = new ArrayList<List<Cell>>();
	}

	public Field(int numberOfLiveShipParts) {
		this();
		this.numberOfLiveShipParts = numberOfLiveShipParts;
		fillField();
	}

	protected abstract void fillField();

	protected void fillField(Cell type, int sideLength) {
		for (int i = 0; i < sideLength; i++) {
			List<Cell> row = new ArrayList<Cell>();
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
			for (List<Cell> row : field) {
				for (Cell element : row) {
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
		Field other = (Field) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (numberOfLiveShipParts != other.numberOfLiveShipParts)
			return false;
		return true;
	}

	protected int count(Cell type) {
		int count = 0;
		for (List<Cell> row : field) {
			for (Cell element : row) {
				if (element == type) {
					count++;
				}
			}
		}
		return count;
	}

	public Cell getCellFieldType(Coordinate coordinate) {
		if ((field.size() < 1)
				|| (coordinate.getY() > getFieldMaxYCoordinate())
				|| (coordinate.getX() > getFieldMaxXCoordinate())) {
			throw new IllegalArgumentException("Coordinate is out of bounds: (" + coordinate.getY() + "," + coordinate.getX() + 
					"). Max: (" + getFieldMaxYCoordinate() + "," + getFieldMaxXCoordinate() + ")");
		}
		return field.get(coordinate.getY()).get(coordinate.getX());
	}

	public void setCellFieldType(Coordinate coordinate, Cell type) {
		setUninitializedCells(coordinate, getDefaultFillingType());
		field.get(coordinate.getY()).set(coordinate.getX(), type);
	}

	protected abstract Cell getDefaultFillingType();

	public void setUninitializedCells(Coordinate coordinate,
			Cell defaultFillingFieldType) {
		// the field size is dynamic, so if we want to set a cell, which is out
		// of bounds, then we increasing the size of the field, and fill it with
		// the default FieldType
		for (int i = 0; i <= coordinate.getY(); i++) {
			if (isFieldHasThisRowIndex(coordinate)) {
				field.add(new ArrayList<Cell>());
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
		return count(Cell.HIT) == numberOfLiveShipParts;
	}

	public void addFieldToPosition(List<List<Cell>> field, Coordinate coordinate) {
		int x = coordinate.getX();
		int y = coordinate.getY();

		for (List<Cell> row : field) {
			for (Cell cell : row) {
				this.setCellFieldType(new Coordinate(y, x), cell);
				x++;
			}
			x = coordinate.getX();
			y++;
		}
	}

	public void addLine(String[] cells) {
		List<Cell> row = new ArrayList<Cell>();
		for (String cell : cells) {
			row.add(Cell.getFieldTypeByReadable(cell));
		}
		field.add(row);
	}

	public void generateDeniedCells() {
		this.autoAddEmptyBorder();
		for (int i = 1; i < getFieldMaxYCoordinate(); i++) {
			for (int j = 1; j < getFieldMaxXCoordinate(); j++) {
				if (getCellFieldType(new Coordinate(i, j)) == Cell.SHIP_PART) {
					setDeniedCell(i - 1, j);
					setDeniedCell(i + 1, j);
					setDeniedCell(i, j - 1);
					setDeniedCell(i, j + 1);
				}
			}
		}
	}

	private void setDeniedCell(int i, int j) {
		Coordinate shouldBeDeniedCell = new Coordinate(i, j);
		if (getCellFieldType(shouldBeDeniedCell) != Cell.SHIP_PART) {
			setCellFieldType(shouldBeDeniedCell, Cell.DENIED);
		}
	}

	public void autoAddEmptyBorder() {
		if (!field.isEmpty()) {
			if (!isTopLineEmpty()) {
				addEmptyTopLine();
			}
			if (!isLeftLineEmpty()) {
				addEmptyLeftLine();
			}
			if (!isRightLineEmpty()) {
				addEmptyRightLine();
			}
			if (!isBottomLineEmpty()) {
				addEmptyBottomLine();
			}
		}
	}

	private void addEmptyBottomLine() {
		this.setUninitializedCells(new Coordinate(getFieldMaxYCoordinate() + 1,
				getFieldMaxXCoordinate()), Cell.EMPTY);
	}

	private boolean isBottomLineEmpty() {
		return !field.get(getFieldMaxYCoordinate()).contains(Cell.SHIP_PART);
	}

	private void addEmptyRightLine() {
		this.setUninitializedCells(new Coordinate(getFieldMaxYCoordinate(),
				getFieldMaxXCoordinate() + 1), Cell.EMPTY);
	}

	private boolean isRightLineEmpty() {
		boolean result = true;
		for (List<Cell> row : field) {
			if (row.get(this.getFieldMaxXCoordinate()) == Cell.SHIP_PART) {
				result = false;
			}
		}
		return result;
	}

	private void addEmptyLeftLine() {
		Ship newShip = new Ship();
		newShip.setUninitializedCells(new Coordinate(getFieldMaxYCoordinate(),
				0), Cell.EMPTY);
		newShip.addFieldToPosition(field, new Coordinate(0, 1));
		field = newShip.getField();
	}

	private boolean isLeftLineEmpty() {
		boolean result = true;
		for (List<Cell> row : field) {
			if (row.get(0) == Cell.SHIP_PART) {
				result = false;
			}
		}
		return result;
	}

	private void addEmptyTopLine() {
		Ship newShip = new Ship();
		newShip.setUninitializedCells(new Coordinate(0,
				getFieldMaxXCoordinate()), Cell.EMPTY);
		newShip.addFieldToPosition(field, new Coordinate(1, 0));
		field = newShip.getField();
	}

	private boolean isTopLineEmpty() {
		return !field.get(0).contains(Cell.SHIP_PART);
	}

	public List<List<Cell>> getField() {
		return field;
	}
}
