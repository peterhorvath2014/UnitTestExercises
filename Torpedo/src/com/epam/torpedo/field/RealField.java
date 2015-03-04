package com.epam.torpedo.field;

import java.util.ArrayList;
import java.util.List;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.ship.Ship;
import com.epam.torpedo.util.Utility;

public abstract class RealField implements Field {

	protected List<List<Cell>> field;

	public RealField() {
		field = new ArrayList<List<Cell>>();
	}

	public RealField(GameConfiguration gameConfiguration) {
		this();
		Utility.isParameterNull(gameConfiguration);
		setUninitializedCells(new Coordinate(gameConfiguration.height - 1, gameConfiguration.width - 1),
				getDefaultFillingType());
	}

	@Override
	public int getNumberOfLiveShipParts() {
		return count(new Cell[] { Cell.SHIP_PART, Cell.HIT, Cell.SUNK });
	}

	@Override
	public int getSideLengthX() {
		int sideLength = 0;
		if (field.size() != 0) {
			if (!field.get(0).isEmpty()) {
				sideLength = field.get(0).size();
			}
		}
		return sideLength;
	}

	@Override
	public int getSideLengthY() {
		return field.size();
	}

	@Override
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
		RealField other = (RealField) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		return true;
	}

	protected int count(Cell[] types) {
		int count = 0;
		for (Cell type : types) {
			for (List<Cell> row : field) {
				for (Cell element : row) {
					if (element == type) {
						count++;
					}
				}
			}
		}
		return count;
	}

	@Override
	public Cell getCell(Coordinate coordinate) {
		Cell result = Cell.UNKNOWN;
		if (!isCoordinateOutOfBounds(coordinate)) {
			result = field.get(coordinate.getY()).get(coordinate.getX());
		}
		return result;
	}

	protected boolean isCoordinateOutOfBounds(Coordinate coordinate) {
		return (field.isEmpty() || coordinate.getX() < 0 || coordinate.getY() < 0
				|| coordinate.getX() > getFieldMaxCoordinateX() || coordinate.getY() > getFieldMaxCoordinateY());
	}

	public void setCellRectangle(Coordinate topLeft, Coordinate bottomRight, Cell type) {
		setUninitializedCells(bottomRight, getDefaultFillingType());
		for (int i = topLeft.getY(); i <= bottomRight.getY(); i++) {
			for (int j = topLeft.getX(); j <= bottomRight.getX(); j++) {
				setCell(new Coordinate(i, j), type);
			}
		}

	}

	@Override
	public void setCell(Coordinate coordinate, Cell type) {
		if (isCoordinateOutOfBounds(coordinate)) {
			setUninitializedCells(coordinate, getDefaultFillingType());
		}
		field.get(coordinate.getY()).set(coordinate.getX(), type);
	}

	protected abstract Cell getDefaultFillingType();

	public void setUninitializedCells(Coordinate coordinate, Cell defaultFillingCell) {
		// the field size can grow dynamically, so if we want to set a cell,
		// which is out
		// of bounds, then we increasing the size of the field, and fill it with
		// the default Cell
		int maxCoordinateY = Math.max(coordinate.getY(), getFieldMaxCoordinateY());
		for (int i = 0; i <= maxCoordinateY; i++) {
			if (isFieldHasThisRowIndex(maxCoordinateY)) {
				field.add(new ArrayList<Cell>());
			}
			int maxCoordinateX = Math.max(coordinate.getX(), getFieldMaxCoordinateX());
			for (int j = 0; j <= maxCoordinateX; j++) {
				if (isRowHasThisColumnIndex(i, maxCoordinateX)) {
					field.get(i).add(defaultFillingCell);
				}
			}
		}
	}

	private int getFieldMaxCoordinateY() {
		return field.size() - 1;
	}

	private int getFieldMaxCoordinateX() {
		int result = -1;
		if (!(field.size() == 0 || field.get(0) == null)) {
			result = field.get(0).size() - 1;
		}
		return result;
	}

	private boolean isRowHasThisColumnIndex(int row, int coordinateX) {
		return field.get(row).size() <= coordinateX;
	}

	private boolean isFieldHasThisRowIndex(int coordinateY) {
		return field.size() <= coordinateY;
	}

	@Override
	public boolean isDone(int numberOfLiveShipParts) {
		return getNumberOfLiveShipParts() == numberOfLiveShipParts;
	}

	public void addFieldToPosition(List<List<Cell>> field, Coordinate coordinate) {
		int x = coordinate.getX();
		int y = coordinate.getY();

		for (List<Cell> row : field) {
			for (Cell cell : row) {
				Coordinate actualCoordinate = new Coordinate(y, x);
				if (isCellOverwritable(cell, actualCoordinate)) {
					this.setCell(actualCoordinate, cell);
				}
				x++;
			}
			x = coordinate.getX();
			y++;
		}
	}

	@Override
	public boolean isCellOverwritable(Cell newCell, Coordinate actualCoordinate) {
		Cell cell = null;
		boolean isNonNullCellOverwritable = true;
		try {
			cell = getCell(actualCoordinate);
			isNonNullCellOverwritable = !(cell == Cell.DENIED && newCell == Cell.EMPTY);
		} catch (IllegalArgumentException ex) {
			// cell is out of bounds, so we can overwrite
		}

		return isNonNullCellOverwritable;
	}

	@Override
	public void addLine(String[] cells) {
		List<Cell> row = new ArrayList<Cell>();
		for (String cell : cells) {
			row.add(Cell.getFieldTypeByReadable(cell));
		}
		field.add(row);
	}

	public void generateDeniedCells() {
		this.autoAddEmptyBorder();
		for (int i = 1; i < getFieldMaxCoordinateY(); i++) {
			for (int j = 1; j < getFieldMaxCoordinateX(); j++) {
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
		this.setUninitializedCells(new Coordinate(getFieldMaxCoordinateY() + 1, getFieldMaxCoordinateX()), Cell.EMPTY);
	}

	private boolean isBottomLineEmpty() {
		return !field.get(getFieldMaxCoordinateY()).contains(Cell.SHIP_PART);
	}

	private void addEmptyRightLine() {
		this.setUninitializedCells(new Coordinate(getFieldMaxCoordinateY(), getFieldMaxCoordinateX() + 1), Cell.EMPTY);
	}

	private boolean isRightLineEmpty() {
		boolean result = true;
		for (List<Cell> row : field) {
			if (row.get(this.getFieldMaxCoordinateX()) == Cell.SHIP_PART) {
				result = false;
			}
		}
		return result;
	}

	private void addEmptyLeftLine() {
		Ship newShip = new Ship();
		newShip.setUninitializedCells(new Coordinate(getFieldMaxCoordinateY(), 0), Cell.EMPTY);
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
		newShip.setUninitializedCells(new Coordinate(0, getFieldMaxCoordinateX()), Cell.EMPTY);
		newShip.addFieldToPosition(field, new Coordinate(1, 0));
		field = newShip.getField();
	}

	private boolean isTopLineEmpty() {
		return !field.get(0).contains(Cell.SHIP_PART);
	}

	public List<List<Cell>> getField() {
		return field;
	}

	@Override
	public boolean isShipPart(Coordinate coordinate) {
		return getCell(coordinate) == Cell.SHIP_PART;
	}

	@Override
	public boolean isHit(Coordinate coordinate) {
		return getCell(coordinate) == Cell.HIT;
	}

	@Override
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
}
