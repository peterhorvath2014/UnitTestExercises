package com.epam.torpedo.game;

import java.util.LinkedList;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.battlefield.OwnedBattleField;
import com.epam.torpedo.strategies.Strategy;

public class Game {
	protected GameConfiguration gameConfiguration;
	protected GameState gameState;
	protected Strategy strategy;
	protected LinkedList<Coordinate> attackHistory;

	public Game(GameConfiguration gameConfiguration) {
		this.gameConfiguration = gameConfiguration;
		attackHistory = new LinkedList<Coordinate>();
		OwnedBattleField ownedBattleField = new OwnedBattleField(
				gameConfiguration);
		gameState = new GameState(ownedBattleField, this.gameConfiguration);
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public GameConfiguration getGameConfiguration() {
		return gameConfiguration;
	}

	public String getServerHost() {
		return gameConfiguration.serverHost;
	}

	public int getServerPort() {
		return gameConfiguration.serverPort;
	}

	public int getBattleFieldWidth() {
		return gameConfiguration.width;
	}

	public int getBattleFieldHeight() {
		return gameConfiguration.height;
	}

	public void setBattleFieldWidth(int width) {
		gameConfiguration.width = width;
	}

	public void setBattleFieldHeight(int height) {
		gameConfiguration.height = height;
	}

	public Coordinate getNextAttackingCoordinate() {
		return strategy.getNextAttackingCoordinate(attackHistory);
	}

	public void setGuessedBattleFieldCell(Coordinate coordinate, Cell cell) {
		gameState.setGuessedBattleFieldCell(coordinate, cell);
	}

	public void addAttackToHitory(Coordinate coordinate) {
		attackHistory.add(coordinate);
	}

	@Override
	public String toString() {
		return "Game [gameConfiguration=" + gameConfiguration + ", gameState="
				+ gameState + ", strategy=" + strategy + ", attackHistory="
				+ attackHistory + "]";
	}

	public Cell checkFire(Coordinate coordinate) {
		return gameState.checkFire(coordinate);
	}

	public boolean isHomeDone() {
		return gameState.isHomeDone();
	}

	public boolean isOpponentDone() {
		return gameState.isOpponentDone();
	}

	/*
	 * public Cell shootNext() { return null; Coordinate coordinate =
	 * strategy.getNextAttackingCoordinate(attackHistory);
	 * evaluatePoint(opponentAPI, coordinate); gameState.increaseAttackCount();
	 * if (gameState.isDone()) { gameState.setWon(true); } }
	 */

	/*
	 * protected void evaluatePoint(OpponentAPI opponentAPI, Coordinate
	 * coordinate) { Cell result = opponentAPI.shoot(coordinate);
	 * gameState.setGuessedBattleFieldCell(coordinate, result); }
	 */
}
