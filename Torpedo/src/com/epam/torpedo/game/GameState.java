package com.epam.torpedo.game;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.Field;
import com.epam.torpedo.field.battlefield.GuessedBattleField;
import com.epam.torpedo.field.battlefield.OwnedBattleField;
import com.epam.torpedo.util.Utility;

public class GameState {

	private boolean won;
	private int attackCountHome;
	private int attackHitOpponent;
	private GuessedBattleField guessedBattleField;
	private OwnedBattleField ownedBattleField;

	public GameState(OwnedBattleField ownedBattleField, GameConfiguration gameConfiguration) {
		this(false, 0, new GuessedBattleField(gameConfiguration), ownedBattleField);
	}

	private GameState(boolean won, int attackCountHome,
			GuessedBattleField guessedBattleField, OwnedBattleField ownedBattleField) {
		Utility.isParameterNull(guessedBattleField);
		Utility.isParameterNull(ownedBattleField);
		this.won = won;
		this.attackCountHome = attackCountHome;
		this.guessedBattleField = guessedBattleField;
		this.ownedBattleField = ownedBattleField;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public int getAttackCountHome() {
		return attackCountHome;
	}

	public void setAttackCountHome(int attackCountHome) {
		this.attackCountHome = attackCountHome;
	}

	public GuessedBattleField getGuessedBattleField() {
		return guessedBattleField;
	}

	public void setGuessedBattleField(GuessedBattleField guessedBattleField) {
		this.guessedBattleField = guessedBattleField;
	}

	public Field getOwnedBattleField() {
		return ownedBattleField;
	}

	public void setOwnedBattleField(OwnedBattleField ownedBattleField) {
		this.ownedBattleField = ownedBattleField;
	}

	public void increaseAttackCountHome() {
		this.attackCountHome++;
	}

	@Override
	public String toString() {
		return "GameState [won=" + won + ", attackCountHome=" + attackCountHome
				+ ", attackHitOpponent=" + attackHitOpponent
				+ ", guessedBattleField=" + guessedBattleField
				+ ", ownedBattleField=" + ownedBattleField + "]";
	}

	public int getSideLengthX() {
		return guessedBattleField.getSideLengthX();
	}

	public int getSideLengthY() {
		return guessedBattleField.getSideLengthY();
	}

	public void setGuessedBattleFieldCell(Coordinate coordinate, Cell type) {
		if (type == Cell.SUNK) {
			guessedBattleField.changeAllConnectedHitToSunk(coordinate);
		} else {
			guessedBattleField.setCell(coordinate, type);
		}
	}

	public Cell getGuessedCellFieldType(Coordinate coordinate) {
		return guessedBattleField.getCellFieldType(coordinate);
	}

	public boolean isHomeDone() {
		if (guessedBattleField.isDone(ownedBattleField.getNumberOfLiveShipParts())) {
			won = true;
		}
		return won;
	}

	public Cell checkFire(Coordinate coordinate) {
		Cell cell = ownedBattleField.getCellFieldType(coordinate);
		Cell result = Cell.MISSED;
		if (cell == Cell.SHIP_PART) {
			result = Cell.HIT;
			attackHitOpponent++;
		}
		//TODO SUNK
		return result;
		
	}

	public boolean isOpponentDone() {
		return attackHitOpponent == ownedBattleField.getNumberOfLiveShipParts();
	}

}
