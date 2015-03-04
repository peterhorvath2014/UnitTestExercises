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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameConfiguration == null) ? 0 : gameConfiguration.hashCode());
		result = prime * result + ((gameState == null) ? 0 : gameState.hashCode());
		result = prime * result + ((strategy == null) ? 0 : strategy.hashCode());
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
		Game other = (Game) obj;
		if (gameConfiguration == null) {
			if (other.gameConfiguration != null)
				return false;
		} else if (!gameConfiguration.equals(other.gameConfiguration))
			return false;
		if (gameState == null) {
			if (other.gameState != null)
				return false;
		} else if (!gameState.equals(other.gameState))
			return false;
		if (strategy == null) {
			if (other.strategy != null)
				return false;
		} else if (!strategy.equals(other.strategy))
			return false;
		return true;
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
		return strategy.getNextAttackingCoordinate(gameState.getOwnAttackHistory());
	}

	public void setGuessedOpponentBattleFieldCell(Coordinate coordinate, Cell cell) {
		gameState.setGuessedOpponentBattleFieldCell(coordinate, cell);
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

	public void addAttackToOwnHistory(Coordinate nextAttackingCoordinate) {
		// TODO Auto-generated method stub
		
	}
}
