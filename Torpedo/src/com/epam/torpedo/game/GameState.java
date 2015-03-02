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
	private int attackCountHome;
	protected LinkedList<Coordinate> attackHistoryHome;
	private int hitCountOpponent;
	private GuessedOpponentBattleField guessedOpponentBattleField;
	private HomeBattleField homeBattleField;

	public GameState(HomeBattleField homeBattleField, GameConfiguration gameConfiguration) {
		Utility.isParameterNull(guessedOpponentBattleField);
		Utility.isParameterNull(homeBattleField);
		this.won = false;
		this.attackHistoryHome = new LinkedList<Coordinate>();
		this.guessedOpponentBattleField = new GuessedOpponentBattleField(gameConfiguration);
		this.homeBattleField = homeBattleField;
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

	public GuessedOpponentBattleField getGuessedBattleField() {
		return guessedOpponentBattleField;
	}

	public void setGuessedOpponentBattleField(GuessedOpponentBattleField guessedOpponentBattleField) {
		this.guessedOpponentBattleField = guessedOpponentBattleField;
	}

	public Field gethomeBattleField() {
		return homeBattleField;
	}

	public void setHomeBattleField(HomeBattleField homeBattleField) {
		this.homeBattleField = homeBattleField;
	}

	public void increaseAttackCountHome() {
		this.attackCountHome++;
	}

	@Override
	public String toString() {
		return "GameState [won=" + won + ", attackCountHome=" + attackCountHome + ", attackHistoryHome="
				+ attackHistoryHome + ", hitCountOpponent=" + hitCountOpponent + ", guessedOpponentBattleField="
				+ guessedOpponentBattleField + ", homeBattleField=" + homeBattleField + "]";
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
		Cell cell = homeBattleField.getCell(coordinate);
		Cell result = Cell.MISSED;
		if (cell == Cell.SHIP_PART) {
			result = Cell.HIT;
			// TODO SUNK
			hitCountOpponent++;
		}
		return result;
	}

	public boolean isOpponentDone() {
		return hitCountOpponent == homeBattleField.getNumberOfLiveShipParts();
	}

	public void addAttackHistory(Coordinate coordinate) {
		attackHistoryHome.add(coordinate);
	}

	public LinkedList<Coordinate> getAttackHistory() {
		return attackHistoryHome;
	}
}
