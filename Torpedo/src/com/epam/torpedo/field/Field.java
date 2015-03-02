package com.epam.torpedo.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.ship.Ship;
import com.epam.torpedo.util.Utility;

public abstract class Field {

	protected List<List<Cell>> field;

	protected static Random random = new Random();

	public Field() {
		field = new ArrayList<List<Cell>>();
	}

	public Field(GameConfiguration gameConfiguration) {
		this();
		Utility.isParameterNull(gameConfiguration);
		setUninitializedCells(new Coordinate(gameConfiguration.height - 1,
				gameConfiguration.width - 1), getDefaultFillingType());
	}

	public int getNumberOfLiveShipParts() {
		return count(Cell.SHIP_PART) + count(Cell.HIT) + count(Cell.SUNK);
	}

	public int getSideLengthX() {
		int sideLength = 0;
		if (field.size() != 0) {
			if (!field.get(0).isEmpty()) {
				sideLength = field.get(0).size();
			}
		}
		return sideLength;
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

	public Cell getCell(Coordinate coordinate) {
		if ((field.size() < 1)
				|| (coordinate.getY() > getFieldMaxYCoordinate())
				|| (coordinate.getX() > getFieldMaxXCoordinate())) {
			throw new IllegalArgumentException("Coordinate is out of bounds: ("
					+ coordinate.getY() + "," + coordinate.getX() + "). Max: ("
					+ getFieldMaxYCoordinate() + "," + getFieldMaxXCoordinate()
					+ ")");
		}
		return field.get(coordinate.getY()).get(coordinate.getX());
	}

	public void setCellRectangle(Coordinate topLeft, Coordinate bottomRight,
			Cell type) {
		setUninitializedCells(bottomRight, getDefaultFillingType());
		for (int i = topLeft.getY(); i <= bottomRight.getY(); i++) {
			for (int j = topLeft.getX(); j <= bottomRight.getX(); j++) {
				setCell(new Coordinate(i, j), type);
			}
		}

	}

	public void setCell(Coordinate coordinate, Cell type) {
		// TODO check if necessary to call setUnitializedCells
		setUninitializedCells(coordinate, getDefaultFillingType());
		field.get(coordinate.getY()).set(coordinate.getX(), type);
	}

	public void changeAllConnectedHitToSunk(Coordinate coordinate) {
		setCell(coordinate, Cell.SUNK);
		// recursive call to all surrounding cell which has HIT on it
		for (int i = coordinate.getY() - 1; i <= coordinate.getY() + 1; i++) {
			for (int j = coordinate.getX() - 1; j <= coordinate.getX() + 1; j++) {
				Coordinate connectedCoordinate = new Coordinate(i, j);
				if (!isCoordinateOutOfBounds(connectedCoordinate)) {
					if (isHit(connectedCoordinate)) {
						changeAllConnectedHitToSunk(connectedCoordinate);
					}
				}
			}
		}
	}

	private boolean isCoordinateOutOfBounds(Coordinate connectedCoordinate) {
		return (connectedCoordinate.getX() < 0
				|| connectedCoordinate.getY() < 0
				|| connectedCoordinate.getX() > getFieldMaxXCoordinate() || connectedCoordinate
				.getY() > getFieldMaxYCoordinate());
	}

	protected abstract Cell getDefaultFillingType();

	public void setUninitializedCells(Coordinate coordinate,
			Cell defaultFillingCell) {
		// the field size is dynamic, so if we want to set a cell, which is out
		// of bounds, then we increasing the size of the field, and fill it with
		// the default Cell
		for (int i = 0; i <= coordinate.getY(); i++) {
			if (isFieldHasThisRowIndex(coordinate)) {
				field.add(new ArrayList<Cell>());
			}
			while (isRowHasThisColumnIndex(i, coordinate)) {
				field.get(i).add(defaultFillingCell);
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

	public boolean isDone(int numberOfLiveShipParts) {
		return getNumberOfLiveShipParts() == numberOfLiveShipParts;
	}

	public void addFieldToPosition(List<List<Cell>> field, Coordinate coordinate) {
		int x = coordinate.getX();
		int y = coordinate.getY();

		for (List<Cell> row : field) {
			for (Cell cell : row) {
				// TODO check EMPTY and DENIED, EMTPY cannot override DENIED
				this.setCell(new Coordinate(y, x), cell);
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
				if (getCell(new Coordinate(i, j)) == Cell.SHIP_PART) {
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
		if (getCell(shouldBeDeniedCell) != Cell.SHIP_PART) {
			setCell(shouldBeDeniedCell, Cell.DENIED);
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

	protected void createShipPart(Coordinate coordinate) {
		setCell(coordinate, Cell.SHIP_PART);
	}

	public boolean isShipPart(Coordinate coordinate) {
		return getCell(coordinate) == Cell.SHIP_PART;
	}

	public boolean isHit(Coordinate coordinate) {
		return getCell(coordinate) == Cell.HIT;
	}
}
