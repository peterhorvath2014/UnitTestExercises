package com.epam.torpedo.game;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.battlefield.HomeBattleField;
import com.epam.torpedo.strategies.Strategy;

public class Game {
	protected GameConfiguration gameConfiguration;
	protected GameState gameState;
	protected Strategy strategy;

	public Game(GameConfiguration gameConfiguration) {
		this.gameConfiguration = gameConfiguration;
		resetGame();
	}

	public void resetGame() {
		HomeBattleField homeBattleField = new HomeBattleField(
				gameConfiguration);
		gameState = new GameState(homeBattleField, this.gameConfiguration);
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
		return strategy.getNextAttackingCoordinate(gameState.getAttackHistory());
	}

	public void setGuessedOpponentBattleFieldCell(Coordinate coordinate, Cell cell) {
		gameState.setGuessedOpponentBattleFieldCell(coordinate, cell);
	}

	public void addAttackToHitory(Coordinate coordinate) {
		gameState.addAttackHistory(coordinate);
	}

	@Override
	public String toString() {
		return "Game [gameConfiguration=" + gameConfiguration + ", gameState="
				+ gameState + ", strategy=" + strategy + "]";
	}

	public Cell checkFireOnHome(Coordinate coordinate) {
		return gameState.checkFireOnHome(coordinate);
	}

	public boolean isHomeDone() {
		return gameState.isHomeDone();
	}

	public boolean isOpponentDone() {
		return gameState.isOpponentDone();
	}

	public boolean isWon() {
		return gameState.isWon();
	}
}
