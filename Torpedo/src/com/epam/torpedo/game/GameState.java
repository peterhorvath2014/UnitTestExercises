package com.epam.torpedo.game;

import java.util.LinkedList;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.Field;
import com.epam.torpedo.field.battlefield.GuessedOpponentBattleField;
import com.epam.torpedo.field.battlefield.HomeBattleField;
import com.epam.torpedo.util.Utility;

public class GameState {

	private boolean won;
	private Field guessedOpponentBattleField;
	private HomeBattleField homeBattleField;

	public GameState(HomeBattleField homeBattleField, GameConfiguration gameConfiguration) {
		Utility.isParameterNull(homeBattleField);
		Utility.isParameterNull(gameConfiguration);
		this.won = false;
		this.guessedOpponentBattleField = new GuessedOpponentBattleField();
		this.homeBattleField = homeBattleField;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((guessedOpponentBattleField == null) ? 0 : guessedOpponentBattleField.hashCode());
		result = prime * result + ((homeBattleField == null) ? 0 : homeBattleField.hashCode());
		result = prime * result + (won ? 1231 : 1237);
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
		GameState other = (GameState) obj;
		if (guessedOpponentBattleField == null) {
			if (other.guessedOpponentBattleField != null)
				return false;
		} else if (!guessedOpponentBattleField.equals(other.guessedOpponentBattleField))
			return false;
		if (homeBattleField == null) {
			if (other.homeBattleField != null)
				return false;
		} else if (!homeBattleField.equals(other.homeBattleField))
			return false;
		if (won != other.won)
			return false;
		return true;
	}

	public boolean isWon() {
		return won;
	}

	private void setWon(boolean won) {
		this.won = won;
	}

	private int getAttackCountHome() {
		return homeBattleField.getAttackHistoryLength();
	}

	private Field getGuessedBattleField() {
		return guessedOpponentBattleField;
	}

	private void setGuessedOpponentBattleField(Field guessedOpponentBattleField) {
		this.guessedOpponentBattleField = guessedOpponentBattleField;
	}

	private Field gethomeBattleField() {
		return homeBattleField;
	}

	private void setHomeBattleField(HomeBattleField homeBattleField) {
		this.homeBattleField = homeBattleField;
	}

	@Override
	public String toString() {
		return "GameState [won=" + won + ", guessedOpponentBattleField=" + guessedOpponentBattleField
				+ ", homeBattleField=" + homeBattleField + "]";
	}

	public int getSideLengthX() {
		return guessedOpponentBattleField.getSideLengthX();
	}

	public int getSideLengthY() {
		return guessedOpponentBattleField.getSideLengthY();
	}

	public void setGuessedOpponentBattleFieldCell(Coordinate coordinate, Cell type) {
		if (type == Cell.SUNK) {
			guessedOpponentBattleField.changeAllConnectedHitToSunk(coordinate);
		} else {
			guessedOpponentBattleField.setCell(coordinate, type);
		}
	}

	public Cell getGuessedOpponentCell(Coordinate coordinate) {
		return guessedOpponentBattleField.getCell(coordinate);
	}

	public boolean isHomeDone() {
		if (guessedOpponentBattleField.isDone(homeBattleField.getNumberOfLiveShipParts())) {
			won = true;
		}
		return won;
	}

	public Cell checkFireOnHome(Coordinate coordinate) {
		Cell result = homeBattleField.checkFire(coordinate);
		return result;

	}

	public boolean isOpponentDone() {
		return homeBattleField.isEveryShipSunk();
	}

	public LinkedList<Coordinate> getOwnAttackHistory() {
		return homeBattleField.getAttackHistory();
	}

}
