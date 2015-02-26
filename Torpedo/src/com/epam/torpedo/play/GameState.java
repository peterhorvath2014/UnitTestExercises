package com.epam.torpedo.play;

import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.FieldType;
import com.epam.torpedo.field.battlefield.GuessedBattleField;
import com.epam.torpedo.field.battlefield.OwnedBattleField;
import com.epam.torpedo.util.Utility;

public class GameState {
	public GameState(OwnedBattleField ownedBattleField) {
		this(false, 0, new GuessedBattleField(), ownedBattleField);
	}

	private GameState(boolean won, int attackCount,
			GuessedBattleField guessedBattleField,
			OwnedBattleField ownedBattleField) {
		Utility.isParameterNull(guessedBattleField);
		Utility.isParameterNull(ownedBattleField);
		this.won = won;
		this.attackCount = attackCount;
		this.guessedBattleField = guessedBattleField;
		this.ownedBattleField = ownedBattleField;
	}

	private boolean won;
	private int attackCount;
	private GuessedBattleField guessedBattleField;
	private OwnedBattleField ownedBattleField;

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public int getAttackCount() {
		return attackCount;
	}

	public void setAttackCount(int attackCount) {
		this.attackCount = attackCount;
	}

	public GuessedBattleField getGuessedBattleField() {
		return guessedBattleField;
	}

	public void setGuessedBattleField(GuessedBattleField guessedBattleField) {
		this.guessedBattleField = guessedBattleField;
	}

	public OwnedBattleField getOwnedBattleField() {
		return ownedBattleField;
	}

	public void setOwnedBattleField(OwnedBattleField ownedBattleField) {
		this.ownedBattleField = ownedBattleField;
	}

	public void increaseAttackCount() {
		this.attackCount++;
	}

	@Override
	public String toString() {
		return "GameResult [won=" + won + ", attackCount=" + attackCount
				+ ", guessedBattleField=" + guessedBattleField
				+ ", ownedBattleField=" + ownedBattleField + "]";
	}

	public int getSideLengthX() {
		return guessedBattleField.getSideLengthX();
	}
	public int getSideLengthY() {
		return guessedBattleField.getSideLengthX();
	}

	public void foundShipPart(Coordinate coordinate) {
		guessedBattleField.setCellFieldType(coordinate, FieldType.HIT);
	}

	public FieldType getGuessedCellFieldType(Coordinate coordinate) {
		return guessedBattleField.getCellFieldType(coordinate);
	}

}
