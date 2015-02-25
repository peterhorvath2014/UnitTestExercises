package com.epam.torpedo.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractField {

	protected List<List<FieldType>> field;

	protected static Random random = new Random();

	protected int numberOfLiveShipParts;

	public AbstractField() {
	}

	public AbstractField(int numberOfLiveShipParts) {
		this.numberOfLiveShipParts = numberOfLiveShipParts;
		field = new ArrayList<List<FieldType>>();
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
		return field.size();
	}
	public int getSideLengthY() {
		return sideLengthY;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (List<FieldType> row : field) {
			for (FieldType element: row) {
				sb.append(element.getReadable() + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + numberOfLiveShipParts;
		result = prime * result + sideLength;
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
		if (sideLength != other.sideLength)
			return false;
		return true;
	}

	protected int count(FieldType type) {
		int count = 0;
		for (List<FieldType> row : field) {
			for (FieldType element: row) {
				if (element == type) {
					count++;
				}
			}
		}
		return count;
	}

	public FieldType getCellFieldType(Coordinate coordinate) {
		return field.get(coordinate.getX()).get(coordinate.getY());
	}
	
	public void setCellFieldType(Coordinate coordinate, FieldType type) {
		field.get(coordinate.getX()).set(coordinate.getY(), type);
	}

	public boolean isDone() {
		return count(FieldType.HIT) == numberOfLiveShipParts;
	}
}
