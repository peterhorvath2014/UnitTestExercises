package com.epam.torpedo.field.battlefield;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Field;

public class OwnedBattleField extends Field {
	
	public OwnedBattleField() {
		super();
	}

	public OwnedBattleField(GameConfiguration gameConfiguration) {
		super(gameConfiguration);
		//fillFieldWithShipsFromFileOnRandomPosition();
	}

	@Override
	protected Cell getDefaultFillingType() {
		return Cell.EMPTY;
	}

}
