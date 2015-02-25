package com.epam.torpedo.field.ship;

import java.util.ArrayList;
import java.util.List;

import com.epam.torpedo.field.AbstractField;
import com.epam.torpedo.field.FieldType;

public class Ship extends AbstractField {

	@Override
	protected void fillField() {
		//We don't know how big is the ship which we read from the file, so we leave the "field" empty
	}

	public void addLine(String[] cells) {
		List<FieldType> row = new ArrayList<FieldType>(); 
		for (String cell : cells) {
			row.add(FieldType.valueOf(cell));
		}
		field.add(row);
	}

}
